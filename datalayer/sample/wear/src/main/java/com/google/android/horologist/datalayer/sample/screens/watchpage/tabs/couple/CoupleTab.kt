package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.CircleIconButton

@Composable
fun CoupleTab(
    onWalkClick: () -> Unit,
    onTimeTogetherClick: () -> Unit,
    onEmojiClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingRatio: Float = 0.135f
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val dynamicPadding = screenWidth * paddingRatio

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dynamicPadding)
        ) {
            // 상단 버튼
            CircleIconButton(
                text = "산책",
                onClick = onWalkClick,
                baseColor  = Color(0xFF8BC34A),
                icon = Icons.Default.Terrain,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )

            // 좌하단 버튼
            CircleIconButton(
                text = "시간",
                onClick = onTimeTogetherClick,
                baseColor  = Color(0xFF64B5F6),
                icon = Icons.Default.Timer, // 타이머 아이콘 이미지
                modifier = Modifier.align(Alignment.BottomStart)
            )

            // 우하단 버튼
            CircleIconButton(
                text = "이모지",
                onClick = onEmojiClick,
                baseColor  = Color(0xFFFFC107),
                icon = Icons.Default.EmojiEmotions, // 이모지 아이콘 이미지
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}