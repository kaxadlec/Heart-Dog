package com.google.android.horologist.datalayer.sample.screens.hotdog.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.components.LoginComponent // 필요 시 추가

@Composable
fun LoginScreen(navController: NavHostController) {
    LoginComponent(navController = navController)
}