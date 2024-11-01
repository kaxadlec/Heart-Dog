// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PetTab(
    modifier: Modifier = Modifier,
    onNavigateToFeed: () -> Unit,
    onNavigateToCall: () -> Unit
) {
     Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onNavigateToFeed) {
            Text("하트 먹이기",
                fontSize = 10.sp,
                )
        }
         Spacer(modifier = Modifier.width(16.dp)) // 버튼 간격
         
        Button(onClick = onNavigateToCall) {
            Text("부르기",
                fontSize = 10.sp,
                )
        }
    }
}