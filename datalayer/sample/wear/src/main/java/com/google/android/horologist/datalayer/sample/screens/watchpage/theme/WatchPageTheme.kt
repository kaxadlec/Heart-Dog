package com.google.android.horologist.datalayer.sample.screens.watchpage.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Typography
import com.google.android.horologist.datalayer.sample.R

// 커스텀 폰트 설정
val GyeonggiBatang = FontFamily(
    Font(R.font.line_seed_kr_bold, FontWeight.Normal)
)

// WatchPage 전용 Typography
val WatchPageTypography = Typography(
    defaultFontFamily = GyeonggiBatang
)

// WatchPage 전용 Theme
@Composable
fun WatchPageTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = WatchPageTypography,
        content = content
    )
}
