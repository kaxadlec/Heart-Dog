// GameTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.CircleIconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun GameTab(
    modifier: Modifier = Modifier,
    onNavigateToSingle: () -> Unit,
    onNavigateToCouple: () -> Unit,
    paddingRatio: Float = 0.05f
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val dynamicPadding = screenWidth * paddingRatio

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dynamicPadding),
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
