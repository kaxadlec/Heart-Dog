package com.google.android.horologist.datalayer.sample.screens.hotdog.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.size.Size

import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.Matching

@Composable
fun LoginComponent(navController: NavController) {

    val context = LocalContext.current

    // 커스텀 ImageLoader 생성하여 GIF 애니메이션 지원
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.move_logo) // main_logo.gif 리소스
                .size(Size.ORIGINAL) // 원래 크기 유지
                .build(),
            contentDescription = null,
            imageLoader = imageLoader,
            modifier = Modifier
                .width(400.dp)
                .height(400.dp)
                .offset(y = (-50).dp),
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.login_button),
            contentDescription = null,
            modifier = Modifier
                .width(370.dp)
                .height(120.dp)
                .offset(y = 170.dp)
                .clickable(
                    indication = null, // 클릭 피드백 제거
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.navigate(Matching)
                },
            contentScale = ContentScale.Fit
        )
    }
}