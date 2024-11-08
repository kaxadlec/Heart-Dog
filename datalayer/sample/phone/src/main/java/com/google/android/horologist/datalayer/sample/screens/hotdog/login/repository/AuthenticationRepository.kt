package com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository

interface AuthenticationRepository {
    suspend fun signInWithGoogle(): Boolean
    suspend fun signInWithKakao(): Boolean
}