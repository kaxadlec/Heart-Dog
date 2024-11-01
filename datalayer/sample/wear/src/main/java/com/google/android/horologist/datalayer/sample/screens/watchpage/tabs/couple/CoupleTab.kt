package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.*
import com.google.android.horologist.datalayer.sample.screens.watchpage.core.common.ui.CircleIconButton
import com.google.android.horologist.datalayer.sample.R

@Composable
fun CoupleTab(
    onWalkClick: () -> Unit,
    onTimeTogetherClick: () -> Unit,
    onEmojiClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(27.dp)
        ) {
            // 상단 버튼
            CircleIconButton(
                text = "함께 산책하기",
                onClick = onWalkClick,
                icon = Icons.Default.DirectionsWalk,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // 좌하단 버튼
            CircleIconButton(
                text = "함께한 시간",
                onClick = onTimeTogetherClick,
                icon = Icons.Default.Timer, // 타이머 아이콘 이미지
                modifier = Modifier.align(Alignment.BottomStart)
            )

            // 우하단 버튼
            CircleIconButton(
                text = "이모지 보내기",
                onClick = onEmojiClick,
                iconResId = R.drawable.heart,  // 하트 이모지 아이콘 이미지
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}