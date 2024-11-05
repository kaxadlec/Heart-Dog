package com.google.android.horologist.datalayer.sample.screens.hotdog.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components.NotificationPage
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository

@Composable
fun NotificationScreen(navController: NavHostController) {
    val notificationRepository = NotificationRepository()
    var notifications by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        val fetchedNotifications = notificationRepository.getNotificationsByUserId(1) // userID가 1인 사용자의 알림을 가져옴
        val typeIds = fetchedNotifications.map { it.typeId }
        val notificationTypes = notificationRepository.getNotificationContentByTypeIds(typeIds)

        // category만 포함하도록 수정
        notifications = fetchedNotifications.map { notification ->
            notificationTypes.find { it.typeId == notification.typeId }?.category ?: "Unknown"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE5B4)),
        contentAlignment = Alignment.Center
    ) {
        NotificationPage(notifications = notifications)
    }

    ButtonFooter(navController = navController)
}