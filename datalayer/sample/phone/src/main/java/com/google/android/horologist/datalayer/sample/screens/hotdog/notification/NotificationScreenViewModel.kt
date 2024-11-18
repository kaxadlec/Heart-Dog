package com.google.android.horologist.datalayer.sample.screens.hotdog.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationScreenViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {
    private val _notifications = MutableStateFlow<List<NotificationUiState>>(emptyList())
    val notifications: StateFlow<List<NotificationUiState>> = _notifications

    // UI 상태를 위한 데이터 클래스에 typeId 추가
    data class NotificationUiState(
        val content: String,
        val category: String,
        val timestamp: String? = null,
        val typeId: Int  // typeId 추가
    )

    init {
        fetchUserNotifications()
    }

    private fun fetchUserNotifications() {
        viewModelScope.launch {
            try {
                val userId = userSessionManager.getCurrentUserId()

                if (userId != null) {
                    // 알림 데이터 가져오기
                    val fetchedNotifications =
                        notificationRepository.getNotificationsByUserId(userId)

                    if (fetchedNotifications.isNotEmpty()) {
                        // 타입 ID 추출
                        val typeIds = fetchedNotifications.map { it.typeId }

                        // 알림 타입 정보 가져오기
                        val notificationTypes =
                            notificationRepository.getNotificationContentByTypeIds(typeIds)

                        // UI 상태로 변환
                        val processedNotifications = fetchedNotifications.map { notification ->
                            NotificationUiState(
                                content = notification.content,
                                category = notificationTypes.find { it.typeId == notification.typeId }?.category
                                    ?: "Unknown",
                                timestamp = notification.createdAt,
                                typeId = notification.typeId  // typeId 포함
                            )
                        }.sortedByDescending { it.timestamp }  // 최신 알림이 위에 오도록 정렬

                        _notifications.value = processedNotifications
                    } else {
                        _notifications.value = emptyList()
                    }
                } else {
                    _notifications.value = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _notifications.value = emptyList()
            }
        }
    }

    fun refreshNotifications() {
        fetchUserNotifications()
    }

    companion object {
        private const val TAG = "NotificationScreenVM"
        const val EMOJI_TYPE_ID_1 = 6  // 이모티콘 타입 ID 상수 추가
        const val EMOJI_TYPE_ID_2 = 7  // 두 번째 이모티콘 타입 ID 상수 추가
    }
}