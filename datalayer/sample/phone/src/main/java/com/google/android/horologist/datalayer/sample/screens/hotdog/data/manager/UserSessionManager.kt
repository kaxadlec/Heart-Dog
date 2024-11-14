package com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUserId(userId: Long) {
        sharedPreferences.edit().putLong("current_user_id", userId).apply()
    }

    fun getCurrentUserId(): Long? {
        val userId = sharedPreferences.getLong("current_user_id", -1L)
        return if (userId == -1L) null else userId
    }

    // 강아지 ID 저장
    fun saveDogId(dogId: Long) {
        sharedPreferences.edit().putLong("current_dog_id", dogId).apply()
    }

    // 강아지 ID 가져오기
    fun getCurrentDogId(): Long? {
        val dogId = sharedPreferences.getLong("current_dog_id", -1L)
        return if (dogId == -1L) null else dogId
    }


}