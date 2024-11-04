package com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun NotificationPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(96.dp))

        // Notification items
        NotificationItem(text = "밥 시간이야! 밥 줘!")
        Spacer(modifier = Modifier.height(8.dp))
        NotificationItem(text = "자네 남자친구가 이모티콘을 보냈다네..")
        Spacer(modifier = Modifier.height(8.dp))
        NotificationItem(text = "산책하지 않을래?")
    }
}

@Composable
fun NotificationItem(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFF5ECD5),
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
            // Icon placeholder
            Image(
                painter = painterResource(R.drawable.hotdog), // Replace with your icon resource
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )

            // Notification text
            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor
            )
        }
    }
}
