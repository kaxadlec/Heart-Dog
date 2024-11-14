package com.google.android.horologist.datalayer.sample.screens.emoji

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.NotificationViewModel

@Composable
fun EmojiTestScreen(
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Emoji Test Screen")

        Spacer(modifier = Modifier.height(16.dp))

        // 새로운 테스트 알림 전송 버튼
        Button(onClick = {
            notificationViewModel.sendStepNotification(senderId = 18)
        }) {
            Text(text = "Send Walk Notification")
        }

        // 이모티콘 알림 전송 버튼
        Button(onClick = {
            notificationViewModel.sendEmojiNotification(senderId = 18)
        }) {
            Text(text = "Send Dog Call Notification")
        }
    }
}
