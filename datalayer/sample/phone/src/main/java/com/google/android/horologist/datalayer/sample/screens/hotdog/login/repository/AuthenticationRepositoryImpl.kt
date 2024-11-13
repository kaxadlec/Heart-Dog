package com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository

import android.util.Log
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.Kakao
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth,
    private val supabaseClient: SupabaseClient
) : AuthenticationRepository {

    override suspend fun signInWithGoogle(): Boolean {
        return try {
            Log.d("Auth", "Starting Google sign in")
            // 1. Google 로그인
            auth.signInWith(Google)
            Log.d("Auth", "Google sign in successful")

            // 2. 현재 인증된 사용자 정보 가져오기
            val supabaseUser = auth.retrieveUserForCurrentSession(updateSession = true)
            Log.d("Auth", "Retrieved user: ${supabaseUser.id}")

            // 3. users 테이블에서 사용자 확인
            val existingUser = supabaseClient
                .from("users")
                .select {
                    filter {
                        filter(column = "users_id_fkey", operator = FilterOperator.EQ, value = supabaseUser.id)
                    }
                }
                .decodeList<User>()
            Log.d("Auth", "Existing users found: ${existingUser.size}")

            // 4. 사용자가 없을 경우에만 생성
            if (existingUser.isEmpty()) {
                Log.d("Auth", "Creating new user")
                val newUser = User(
                    email = supabaseUser.email ?: "",
                    users_id_fkey = supabaseUser.id,
                    matching = false
                )

                supabaseClient.from("users")
                    .insert(newUser)
                Log.d("Auth", "New user created")
            }

            true
        } catch (e: Exception) {
            Log.e("Auth", "Google sign in error: ${e.message}", e)
            false
        }
    }

//    override suspend fun signInWithGoogle(): Boolean {
//        return try {
//            auth.signInWith(Google)
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }

    override suspend fun signInWithKakao(): Boolean {
        return try {
            auth.signInWith(Kakao)
            true
        } catch (e: Exception) {
            false
        }
    }
}