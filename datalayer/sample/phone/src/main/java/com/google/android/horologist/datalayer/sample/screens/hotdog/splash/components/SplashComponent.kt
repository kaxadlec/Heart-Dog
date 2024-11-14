package com.google.android.horologist.datalayer.sample.screens.hotdog.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.google.android.horologist.datalayer.sample.R

@Composable
fun SplashComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5C996)), // 배경색 설정
        contentAlignment = Alignment.Center
    ) {
        // 애니메이션 상태 관리
        val heartScale = remember { Animatable(0f) }
        val textScale = remember { Animatable(0f) }
        val starAlpha = remember { Animatable(0f) }

        LaunchedEffect(Unit) {
            heartScale.animateTo(1f, animationSpec = tween(500))
            delay(200)
            textScale.animateTo(1f, animationSpec = tween(500))
            delay(200)
            starAlpha.animateTo(1f, animationSpec = tween(500))
            delay(300)
        }

        // Heart Dog 애니메이션
        Image(
            painter = painterResource(id = R.drawable.heart_dog), // int형 리소스 ID 사용
            contentDescription = null,
            modifier = Modifier
                .width(350.dp)
                .height(80.dp)
                .offset(y = (-35).dp)
                .scale(heartScale.value), // 스케일 애니메이션 적용
            contentScale = ContentScale.Fit
        )

        // Splash Text 애니메이션
        Image(
            painter = painterResource(id = R.drawable.splash_text), // Splash Text.svg
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .offset(y = 60.dp)
                .scale(textScale.value), // 스케일 애니메이션 적용
            contentScale = ContentScale.Fit
        )

        // Splash Star 깜빡임 애니메이션
        Image(
            painter = painterResource(id = R.drawable.splash_star), // Splash Star.svg
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .offset(y = (-65).dp)
                .alpha(starAlpha.value) // 투명도 애니메이션 적용
        )
    }
}
