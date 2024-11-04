package com.google.android.horologist.datalayer.sample.screens.hotdog.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun AfterEffects(navController: NavController, destination: Any, delayMillis: Long = 3000L) {
    LaunchedEffect(Unit) {
        delay(delayMillis)
        navController.navigate(destination) {
            popUpTo("Splash") { inclusive = true }
        }
    }
}
