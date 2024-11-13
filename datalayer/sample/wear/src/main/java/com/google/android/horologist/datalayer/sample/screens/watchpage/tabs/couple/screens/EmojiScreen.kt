package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.R
import androidx.wear.compose.material.Text
import kotlin.math.abs
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.clickable

@Composable
fun EmojiScreen(onEmojiSelected: (String) -> Unit) {
    val emojis = listOf("😐", "😭", "😊", "😕", "😔")
    val repeatedEmojis = remember { List(1000) { emojis[it % emojis.size] } }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = repeatedEmojis.size / 2)

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // 비율로 설정된 크기
    val paddingRatio = 0.05f
    val textSizeRatio = 0.06f
    val largeEmojiSizeRatio = 0.40f
    val smallEmojiSizeRatio = 0.20f
    val spaceBetweenEmojisRatio = 0.04f

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidth * paddingRatio), // padding 비율로 설정
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "당신의 기분을 전달하세요",
                fontSize = (screenWidth * textSizeRatio).value.sp, // 비율로 설정된 텍스트 크기
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = screenWidth * paddingRatio)
            )

            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(screenWidth * spaceBetweenEmojisRatio), // 비율로 설정된 이모지 간격
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(repeatedEmojis.size) { index ->
                    val emoji = repeatedEmojis[index]
                    val centerIndex = listState.firstVisibleItemIndex + 1
                    val distanceFromCenter = abs(centerIndex - index)
                    val size = if (distanceFromCenter == 0) screenWidth * largeEmojiSizeRatio else screenWidth * smallEmojiSizeRatio
                    val alpha = if (distanceFromCenter == 0) 1f else 0.5f
                    EmojiCircle(emoji, size, alpha) {
                        onEmojiSelected(emoji)
                    }
                }
            }
        }
    }
}

@Composable
fun EmojiCircle(emoji: String, size: Dp, alpha: Float, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(size)
            .background(Color(0xFFFFA726).copy(alpha = alpha), CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = emoji,
            fontSize = (size.value / 2).sp,
            color = Color.White
        )
    }
}