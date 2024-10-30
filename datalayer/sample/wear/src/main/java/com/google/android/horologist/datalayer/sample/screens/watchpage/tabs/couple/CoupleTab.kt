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

@Composable
fun CoupleTab(
    onWalkClick: () -> Unit,
    onTimeTogetherClick: () -> Unit,
    onEmojiClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberScalingLazyListState()

    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        autoCentering = androidx.wear.compose.foundation.lazy.AutoCenteringParams(itemIndex = 0)
    ) {
        item {
            CompactChip(
                onClick = onWalkClick,
                label = {
                    Text(
                        "함께 산책하기",
                        textAlign = TextAlign.Center
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.DirectionsWalk,
                        contentDescription = "산책"
                    )
                },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(0.8f)
            )
        }

        item {
            CompactChip(
                onClick = onTimeTogetherClick,
                label = {
                    Text(
                        "함께한 시간",
                        textAlign = TextAlign.Center
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "시간"
                    )
                },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(0.8f)
            )
        }

        item {
            CompactChip(
                onClick = onEmojiClick,
                label = {
                    Text(
                        "이모지 보내기",
                        textAlign = TextAlign.Center
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.EmojiEmotions,
                        contentDescription = "이모지"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
    }
}