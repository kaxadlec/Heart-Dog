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

@Composable
fun EmojiScreen() {
    val emojis = listOf("😐", "😭", "😊", "😕", "😔")
    val repeatedEmojis = remember { List(100) { emojis[it % emojis.size] } }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = repeatedEmojis.size / 2)


    Box(

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "당신의 기분을 전달하세요",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(repeatedEmojis.size) { index ->
                    val emoji = repeatedEmojis[index]
                    val centerIndex = listState.firstVisibleItemIndex + 1
                    val distanceFromCenter = abs(centerIndex - index)
                    val size = if (distanceFromCenter == 0) 70.dp else 40.dp
                    val alpha = if (distanceFromCenter == 0) 1f else 0.5f
                    EmojiCircle(emoji, size, alpha)
                }
            }
        }
    }
}

@Composable
fun EmojiCircle(emoji: String, size: Dp, alpha: Float) {
    Box(
        modifier = Modifier
            .size(size)
            .background(Color(0xFFFFA726).copy(alpha = alpha), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = emoji,
            fontSize = (size.value / 2).sp,
            color = Color.White
        )
    }
}