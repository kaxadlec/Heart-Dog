package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Colors
import com.google.android.horologist.datalayer.sample.R
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import java.util.Calendar

@OptIn(ExperimentalTime::class)
@Composable
fun TimeTogetherScreen(timeTogether: Duration) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }

    // 화면 크기 비율에 따른 크기 설정
    val iconSize = screenWidth * 0.3f  // 화면 너비의 10%
    val topPadding = screenHeight * 0.10f  // 화면 높이의 5%
    val bottomPadding = screenHeight * 0.03f  // 화면 높이의 2%
    val fontSizeSmall = TextUnit(screenWidth.value * 0.11f, TextUnitType.Sp)  // 화면 너비의 4%
    val fontSizeLarge = TextUnit(screenWidth.value * 0.13f, TextUnitType.Sp)

    val hours = timeTogether.inWholeHours
    val minutes = (timeTogether.inWholeMinutes % 60)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding, start = topPadding, end = topPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val icon: Painter = painterResource(id = R.drawable.clock)
        val isNightMode = currentHour in 19..23 || currentHour in 0..6
        val textColor = if (isNightMode) Color.White else Color.Black
        val colors = Colors(
            primary = Color(0xFFFF9A4D),
            onBackground = textColor,
            background = if (isNightMode) Color.Black else Color.White
        )
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(iconSize)
                .padding(bottom = bottomPadding),
        )
        Text(text ="함께한 시간",
            fontSize = fontSizeSmall,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(bottomPadding))

        Text(
            text = "${hours}시간 ${minutes}분",
            fontSize = fontSizeLarge,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}