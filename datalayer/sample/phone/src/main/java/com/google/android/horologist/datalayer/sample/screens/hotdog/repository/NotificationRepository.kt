package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Notification
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.NotificationType
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor() {

    // 기존 함수 1: userId가 일치하는 모든 알림을 리스트로 가져오는 함수
    suspend fun getNotificationsByUserId(userId: Long): List<Notification> = withContext(Dispatchers.IO) {
        try {
            val allNotifications = SupabaseClientProvider.supabase
                .from("notification")
                .select()
                .decodeList<Notification>()
                .filter { it.userId == userId }

            allNotifications
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 기존 함수 2: typeId가 일치하는 category를 가져오는 함수
    suspend fun getNotificationContentByTypeIds(typeIds: List<Int>): List<NotificationType> = withContext(Dispatchers.IO) {
        try {
            val allNotificationTypes = SupabaseClientProvider.supabase
                .from("notification_type")
                .select()
                .decodeList<NotificationType>()
                .filter { it.typeId in typeIds }

            allNotificationTypes
        } catch (e: Exception) {
            emptyList()
        }
    }

    // FCM 토큰 업데이트
    suspend fun updateFcmToken(userId: Long, token: String) = withContext(Dispatchers.IO) {
        try {
            val params = JsonObject(
                mapOf(
                    "p_user_id" to JsonPrimitive(userId),
                    "p_fcm_token" to JsonPrimitive(token)
                )
            )

            SupabaseClientProvider.supabase
                .postgrest
                .rpc("update_fcm_token", params)

            println("FCM token updated successfully")
            true
        } catch (e: Exception) {
            println("Error updating FCM token: ${e.message}")
            false
        }
    }

    // 알림 전송
    suspend fun sendNotification(senderId: Long, typeId: Int, content: String) = withContext(Dispatchers.IO) {
        try {
            val params = JsonObject(
                mapOf(
                    "p_sender_id" to JsonPrimitive(senderId),
                    "p_type_id" to JsonPrimitive(typeId),
                    "p_content" to JsonPrimitive(content)
                )
            )

            SupabaseClientProvider.supabase
                .postgrest
                .rpc("send_notification", params)

            println("Notification sent successfully")
            true
        } catch (e: Exception) {
            println("Error sending notification: ${e.message}")
            false
        }
    }
}