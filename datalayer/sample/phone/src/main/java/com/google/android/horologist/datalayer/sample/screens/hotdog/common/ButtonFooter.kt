package com.google.android.horologist.datalayer.sample.screens.hotdog.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.InsertQRCode
import com.google.android.horologist.datalayer.sample.screens.Notification

@Composable
fun ButtonFooter( navController : NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.footer_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.pet_button),
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .offset(x = 1.dp)
                .clickable (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.navigate(HotDogMain)
                },
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.notification_button),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .offset(x = (-125).dp, y = (-15).dp)
                .clickable (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.navigate(Notification)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.setting_button),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .offset(x = 125.dp, y = (-15).dp)
        )
    }
}
