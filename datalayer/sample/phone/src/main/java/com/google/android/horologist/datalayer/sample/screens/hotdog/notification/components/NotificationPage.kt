package com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.ui.theme.bgBoxColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun NotificationPage(notifications: List<String>) {
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

        // 스크롤 가능한 알림 리스트
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp) // 리스트 위아래 여백 추가
        ) {
            items(notifications) { text ->
                Spacer(modifier = Modifier.height(8.dp)) // 각 항목 위에 마진 추가
                NotificationItem(text = text)
                Spacer(modifier = Modifier.height(8.dp)) // 각 항목 아래에 마진 추가
            }
        }
    }
}


@Composable
fun NotificationItem(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = bgBoxColor,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.hotdog), // Replace with your icon resource
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor
            )
        }
    }
}
