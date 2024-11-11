// screens/single/SingleGameScreen.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.single

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun SingleGameScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(" 싱글 게임 화면인 줄 알았지? ")
        Button(onClick = onBack) {
            Text("뒤로가기")
        }
    }
}