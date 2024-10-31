// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun PetTab(
    modifier: Modifier = Modifier,
    onNavigateToFeed: () -> Unit,
    onNavigateToCall: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToFeed) {
            Text("하트 먹이기")
        }

        Button(onClick = onNavigateToCall) {
            Text("호출하기")
        }
    }
}