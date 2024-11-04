package com.google.android.horologist.datalayer.sample.screens.hotdog.matching.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.CreateQRCode
import com.google.android.horologist.datalayer.sample.screens.InsertQRCode
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.LogoHeader

@Composable
fun MatchingComponent(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){

        LogoHeader(navController = navController)

        Image(
            painter = painterResource(id = R.drawable.create_qrcode),
            contentDescription = null,
            modifier = Modifier
                .width(370.dp)
                .height(120.dp)
                .offset(y = (-45).dp)
                .clickable (
                    indication = null, // 클릭 피드백 제거
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.navigate(CreateQRCode)
                },
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.insert_qrcode),
            contentDescription = null,
            modifier = Modifier
                .width(370.dp)
                .height(120.dp)
                .offset(y = 45.dp)
                .clickable (
                    indication = null, // 클릭 피드백 제거
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.navigate(InsertQRCode)
                },
            contentScale = ContentScale.Fit
        )

    }

}
