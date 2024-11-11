package com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components

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
import com.google.android.horologist.datalayer.sample.ui.theme.bgBoxColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

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
                painter = painterResource(R.drawable.hotdog),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 18.sp,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
