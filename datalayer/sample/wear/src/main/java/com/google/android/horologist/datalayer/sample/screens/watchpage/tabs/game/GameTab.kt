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
import com.google.android.horologist.datalayer.sample.screens.watchpage.core.common.ui.CircleIconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Favorite

@Composable
fun GameTab(
    modifier: Modifier = Modifier,
    onNavigateToSingle: () -> Unit,
    onNavigateToCouple: () -> Unit

) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 23.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleIconButton(
                text = "싱글 게임",
                onClick = onNavigateToSingle,
                icon = Icons.Default.SportsEsports  // 게임 아이콘
            )

            CircleIconButton(
                text = "커플 게임",
                onClick = onNavigateToCouple,
                icon = Icons.Default.Favorite  // 커플용 하트 아이콘
            )
        }
    }
}
