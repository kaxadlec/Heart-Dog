package com.google.android.horologist.datalayer.sample.repository

import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.User
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    // 새 사용자를 users 테이블에 추가하는 함수
    suspend fun insertUser(email: String): Boolean = withContext(Dispatchers.IO) {
        val user = User(email = email)

        runCatching {
            println("Attempting to insert user: $user")

            // 데이터만 삽입하고 응답을 처리하지 않음
            SupabaseClientProvider.supabase
                .from("users")
                .insert(user)

            // 삽입 시 오류가 없으면 성공으로 간주
            true
        }.getOrElse { e ->
            println("Error inserting user: ${e.localizedMessage}")
            e.printStackTrace()
            false
        }
    }

    // 이메일을 기준으로 사용자 정보를 가져오는 함수
    suspend fun getUserByEmail(email: String): User? = withContext(Dispatchers.IO) {
        try {
            val users = SupabaseClientProvider.supabase
                .from("users")
                .select()
                .decodeList<User>()

            val user = users.firstOrNull { it.email == email }
            println("Fetched user: $user")
            user

        } catch (e: Exception) {
            println("Error fetching user by email: ${e.message}")
            null
        }
    }
}
