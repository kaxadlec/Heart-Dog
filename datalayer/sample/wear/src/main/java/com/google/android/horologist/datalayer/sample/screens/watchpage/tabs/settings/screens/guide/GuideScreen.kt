// screens/guide/GuideScreen.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.screens.guide

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun GuideScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("사용방법 화면")

        // 여기에 사용방법 내용 추가

        Button(onClick = onBack) {
            Text("뒤로가기")
        }
    }
}