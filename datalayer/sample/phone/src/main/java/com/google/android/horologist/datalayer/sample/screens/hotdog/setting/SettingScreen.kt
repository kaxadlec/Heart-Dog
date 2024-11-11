package com.google.android.horologist.datalayer.sample.screens.hotdog.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.screens.hotdog.setting.components.SettingPage

@Composable
fun SettingScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE5B4)),
        contentAlignment = Alignment.Center
    ) {
        SettingPage(navController = navController)
    }

    ButtonFooter(navController = navController)
}
