package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import android.util.Log
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Notification
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.NotificationType
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor() {

    suspend fun getNotificationsByUserId(userId: Long): List<Notification> = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Calling get_notifications_by_user_id with userId: $userId")

            val params = JsonObject(mapOf("p_user_id" to JsonPrimitive(userId)))
            val response = SupabaseClientProvider.supabase
                .postgrest
                .rpc("get_notifications_by_user_id", params)

            val notifications = response.decodeList<Notification>()
            Log.d(TAG, "Received notifications: $notifications")
            notifications

        } catch (e: Exception) {
            Log.e(TAG, "Failed to get notifications: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getNotificationContentByTypeIds(typeIds: List<Int>): List<NotificationType> = withContext(Dispatchers.IO) {
        try {
            if (typeIds.isEmpty()) {
                Log.d(TAG, "No type IDs provided")
                return@withContext emptyList()
            }

            Log.d(TAG, "Calling get_notification_types_by_ids with typeIds: $typeIds")

            val params = JsonObject(mapOf("p_type_ids" to JsonPrimitive(typeIds.joinToString(","))))
            val response = SupabaseClientProvider.supabase
                .postgrest
                .rpc("get_notification_types_by_ids", params)

            val types = response.decodeList<NotificationType>()
            Log.d(TAG, "Received notification types: $types")
            types

        } catch (e: Exception) {
            Log.e(TAG, "Failed to get notification types: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

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

            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun sendNotification(senderId: Long?, typeId: Int, content: String) = withContext(Dispatchers.IO) {
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

            true
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        private const val TAG = "NotificationRepo"  // 고유한 TAG 정의
    }
}