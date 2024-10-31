// GameTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun GameTab(
    modifier: Modifier = Modifier,
    onNavigateToSingle: () -> Unit,
    onNavigateToCouple: () -> Unit

) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToSingle) {
            Text("싱글 게임", Modifier.size(100.dp))
        }

        Button(onClick = onNavigateToCouple) {
            Text("커플 게임")
        }
    }
}
