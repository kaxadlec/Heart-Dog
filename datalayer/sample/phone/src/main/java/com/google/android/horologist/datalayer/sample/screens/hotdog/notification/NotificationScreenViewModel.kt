package com.google.android.horologist.datalayer.sample.screens.hotdog.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository

class NotificationViewModel : ViewModel() {
    private val notificationRepository = NotificationRepository()
    private val _notifications = MutableStateFlow<List<String>>(emptyList())
    val notifications: StateFlow<List<String>> = _notifications

    init {
        fetchNotifications()
    }

    private fun fetchNotifications() {
        viewModelScope.launch {
            val fetchedNotifications = notificationRepository.getNotificationsByUserId(1)
            val typeIds = fetchedNotifications.map { it.typeId }
            val notificationTypes = notificationRepository.getNotificationContentByTypeIds(typeIds)

            _notifications.value = fetchedNotifications.map { notification ->
                notificationTypes.find { it.typeId == notification.typeId }?.category ?: "Unknown"
            }
        }
    }
}
