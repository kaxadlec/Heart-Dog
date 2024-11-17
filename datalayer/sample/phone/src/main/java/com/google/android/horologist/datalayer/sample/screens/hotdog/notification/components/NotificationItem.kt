package com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.hotdog.notification.NotificationScreenViewModel
import com.google.android.horologist.datalayer.sample.ui.theme.bgBoxColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor
import androidx.compose.foundation.layout.*
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationItem(notification: NotificationScreenViewModel.NotificationUiState) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = bgBoxColor,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 왼쪽 이미지
            Image(
                painter = painterResource(R.drawable.hotdog),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
            )

            // 오른쪽 컨텐츠
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)  // 줄 간격 조정
            ) {
                // 텍스트와 이모티콘
                if (notification.typeId in listOf(6)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(24.dp)  // 높이 고정
                    ) {
                        Text(
                            text = "상대방이 이모티콘을 보냈어요! ",
                            fontSize = 15.sp,  // 글씨 크기 살짝 조정
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = notification.content,
                            fontSize = 18.sp,  // 이모티콘 크기 조정
                            color = textColor
                        )
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(24.dp)  // 높이 고정
                    ) {
                        Text(
                            text = notification.content,
                            fontSize = 15.sp,  // 글씨 크기 살짝 조정
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // 시간 (우측 정렬)
                notification.timestamp?.let { timestamp ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = formatTimeAgo(timestamp),
                            fontSize = 12.sp,
                            color = textColor.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatTimeAgo(timestamp: String): String {
    return try {
        val dateTime = ZonedDateTime.parse(timestamp)
        val now = ZonedDateTime.now()

        // 시간 차이 계산
        val minutesDiff = ChronoUnit.MINUTES.between(dateTime, now)
        val hoursDiff = ChronoUnit.HOURS.between(dateTime, now)

        when {
            // 1시간 이내
            hoursDiff < 1 -> {
                "${minutesDiff}분 전"
            }
            // 24시간 이내
            hoursDiff < 24 -> {
                "${hoursDiff}시간 전"
            }
            // 24시간 이후
            else -> {
                val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
                dateTime.format(formatter)
            }
        }
    } catch (e: Exception) {
        timestamp // 파싱 실패시 원본 반환
    }
}