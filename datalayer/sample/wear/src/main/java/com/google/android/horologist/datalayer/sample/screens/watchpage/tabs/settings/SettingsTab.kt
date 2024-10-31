// SettingsTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun SettingsTab(
    modifier: Modifier = Modifier,
    onNavigateToGuide: () -> Unit,
    onNavigateToReset: () -> Unit,
    onNavigateToLogout: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToGuide) {
            Text("사용방법")
        }

        Button(onClick = onNavigateToReset) {
            Text("초기화")
        }

        Button(onClick = onNavigateToLogout) {
            Text("로그아웃")
        }
    }
}