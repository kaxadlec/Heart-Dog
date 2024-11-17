package com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.hotdog.notification.NotificationScreenViewModel
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationPage(
    notifications: List<NotificationScreenViewModel.NotificationUiState>  // 타입 수정
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 고정된 로고 이미지
        Image(
            painter = painterResource(R.drawable.alarm_title),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (notifications.isEmpty()) {
            Text(
                text = "알림이 없습니다",
                modifier = Modifier.padding(top = 16.dp),
                color = textColor,
                fontSize = 16.sp
            )
        } else {
            // 스크롤 가능한 알림 리스트
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(top = 24.dp, bottom = 128.dp)
            ) {
                items(notifications) { notification ->
                    Spacer(modifier = Modifier.height(8.dp))
                    NotificationItem(notification = notification)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
