package com.google.android.horologist.datalayer.sample.screens.hotdog.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.Login

@Composable
fun SplashScreen(navController: NavController) {

    // Effect 호출
    AfterEffects(navController = navController, destination = Login)

    // 분리된 SplashComponent 컴포저블 호출
    SplashComponent()
}
