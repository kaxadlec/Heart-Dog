package com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.Kakao
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {

    override suspend fun signInWithGoogle(): Boolean {
        return try {
            auth.signInWith(Google)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signInWithKakao(): Boolean {
        return try {
            auth.signInWith(Kakao)
            true
        } catch (e: Exception) {
            false
        }
    }
}