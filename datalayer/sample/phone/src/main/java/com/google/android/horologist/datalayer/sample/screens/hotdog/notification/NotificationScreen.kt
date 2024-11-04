package com.google.android.horologist.datalayer.sample.screens.hotdog.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components.NotificationPage

@Composable
fun NotificationScreen(navController: NavHostController) {
    val notifications = listOf(
        "밥 시간이야! 밥 줘!",
        "자네 남자친구가 이모티콘을 보냈다네..",
        "산책하지 않을래?",
        "밥 시간이야! 밥 줘!",
        "자네 남자친구가 이모티콘을 보냈다네..",
        "산책하지 않을래?",
        "밥 시간이야! 밥 줘!",
        "자네 남자친구가 이모티콘을 보냈다네..",
        "산책하지 않을래?",
        "밥 시간이야! 밥 줘!",
        "자네 남자친구가 이모티콘을 보냈다네..",
        "산책하지 않을래?"
    )

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
