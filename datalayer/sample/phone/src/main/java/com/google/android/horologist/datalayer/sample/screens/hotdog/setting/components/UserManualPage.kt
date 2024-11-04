package com.google.android.horologist.datalayer.sample.screens.hotdog.setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun UserManualPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE5B4))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "사용 설명서",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            color = textColor
        )
        Text(
            text = "여기에 사용 설명서 내용을 추가하세요.",
            fontSize = 16.sp,
            color = textColor
            )
    }
    ButtonFooter(navController = navController)
}
