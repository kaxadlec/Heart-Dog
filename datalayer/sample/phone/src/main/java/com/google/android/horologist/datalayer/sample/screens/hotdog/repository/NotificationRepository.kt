package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Notification
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.NotificationType
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepository {
    // userId가 1인 모든 알림을 리스트로 가져오는 함수
    suspend fun getNotificationsByUserId(userId: Long): List<Notification> = withContext(Dispatchers.IO) {
        try {
            // 데이터베이스에서 모든 알림을 가져온 후 userId로 필터링
            val allNotifications = SupabaseClientProvider.supabase
                .from("notification")
                .select()
                .decodeList<Notification>()
                .filter { it.userId == userId }

            allNotifications
        } catch (e: Exception) {
            println("userId로 알림 리스트 검색 오류: ${e.message}")
            emptyList()  // 오류 발생 시 빈 리스트 반환
        }
    }

    // typeId가 일치하는 category를 가져오는 함수
    suspend fun getNotificationContentByTypeIds(typeIds: List<Int>): List<NotificationType> = withContext(Dispatchers.IO) {
        try {
            // notification_type 테이블에서 typeId로 필터링된 알림 카테고리를 가져옴
            val allNotificationTypes = SupabaseClientProvider.supabase
                .from("notification_type")
                .select()
                .decodeList<NotificationType>()
                .filter { it.typeId in typeIds }

            allNotificationTypes
        } catch (e: Exception) {
            println("typeId로 알림 카테고리 검색 오류: ${e.message}")
            emptyList()  // 오류 발생 시 빈 리스트 반환
        }
    }
}
