package com.google.android.horologist.datalayer.sample.screens.watchpage.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Typography
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Colors
import java.util.Calendar


// WatchPage 전용 Typography
val WatchPageTypography = Typography(
    defaultFontFamily = FontFamily(
        Font(R.font.goryeong_strawberry, FontWeight.Normal)
    )
)

// WatchPage 전용 Theme
@Composable
fun WatchPageTheme(content: @Composable () -> Unit) {
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val isNightMode = currentHour in 19..23 || currentHour in 0..6

    // 시간대에 따른 텍스트 및 배경 색상 설정
    val textColor = if (isNightMode) Color.White else Color.Black
    val backgroundRes = if (isNightMode) R.drawable.night else R.drawable.background_image

    // Colors 설정
    val colors = Colors(
        primary = Color(0xFFFF9A4D),
        onBackground = textColor,
        background = if (isNightMode) Color.Black else Color.White
    )

    MaterialTheme(
        colors = colors,
        typography = WatchPageTypography,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            content() // 자식 컴포저블 렌더링
        }
    }
}
