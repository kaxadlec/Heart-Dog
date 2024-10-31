package com.google.android.horologist.datalayer.sample.screens.hotdog.matching

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
import com.google.android.horologist.datalayer.sample.screens.Matching
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.LogoHeader

@Composable
fun CreateQRCodeScreen(navController: NavHostController) {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center

    ) {

        LogoHeader(navController = navController)

        // 1. QR 코드 생성
        // 2. 상대방이 인식할때까지 로딩화면
        // 2-1. DB에 들어가게 되면 트리거로 로딩 종료
        // 2-2. 인식안되면 1분뒤 이전화면

        Image(
            painter = painterResource(id = R.drawable.qrcode),
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp),
            contentScale = ContentScale.Fit
        )

    }


}