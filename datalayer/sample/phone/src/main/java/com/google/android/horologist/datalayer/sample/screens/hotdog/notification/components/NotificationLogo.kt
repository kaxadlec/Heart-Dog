package com.google.android.horologist.datalayer.sample.screens.hotdog.notification.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.horologist.datalayer.sample.R

@Composable
fun NotificationLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.alarm_title),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(60.dp)
                .padding(top = 32.dp)
        )
    }
}
