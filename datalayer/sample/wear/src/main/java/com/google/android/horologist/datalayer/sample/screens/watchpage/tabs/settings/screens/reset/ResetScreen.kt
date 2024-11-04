// screens/reset/ResetScreen.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.screens.reset

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun ResetScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("정말 초기화 하시겠습니까?")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onBack) {
                Text("취소")
            }
            Button(onClick = {
                // 초기화 로직 구현
                onBack()
            }) {
                Text("확인")
            }
        }
    }
}