package com.google.android.horologist.datalayer.sample.screens.hotdog.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource

import com.google.android.horologist.datalayer.sample.R

@Composable
fun LogoHeader(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){

        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .width(24.dp)
                .height(30.dp)
                .align(Alignment.TopStart) // 왼쪽 상단에 정렬
                .offset(x = 20.dp, y = 20.dp)
                .clickable(
                    indication = null, // 클릭 피드백 제거
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.popBackStack()
                },
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.logo_header),
            contentDescription = null,
            modifier = Modifier
                .width(350.dp)
                .height(30.dp)
                .offset(y = 20.dp),
            contentScale = ContentScale.Fit
        )

    }

}