package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Notification
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.NotificationType
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepository {
    // userId가 일치하는 모든 알림을 리스트로 가져오는 함수
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

    // typeId가 일치하는 category를 가져오는 함수
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
}
