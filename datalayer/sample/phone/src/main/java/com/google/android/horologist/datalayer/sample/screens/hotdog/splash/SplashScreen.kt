package com.google.android.horologist.datalayer.sample.screens.hotdog.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // LaunchedEffect 활성화
    LaunchedEffect(Unit) {
        delay(100) // 스플래시 화면 유지 시간
        navController.navigate(HotDogMain) {
            popUpTo("Splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to Hotdog App!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
    }
}
