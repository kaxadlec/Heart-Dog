// screens/call/CallScreen.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.screens.call

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun CallScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("호출하기 화면")
        Button(onClick = onBack) {
            Text("뒤로가기")
        }
    }
}