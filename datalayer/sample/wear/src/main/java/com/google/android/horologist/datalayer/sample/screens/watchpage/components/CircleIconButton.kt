package com.google.android.horologist.datalayer.sample.screens.watchpage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.wear.compose.material.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import java.util.Calendar


@Composable
fun CircleIconButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconResId: Int? = null,
    buttonSizeRatio: Float = 0.3f,  // 화면 너비 대비 버튼 크기 비율 원래 0.3
    iconSizeRatio: Float = 0.20f,
    textSizeRatio: Float = 0.07f,
    spacingRatio: Float = 0.02f,
    enabled: Boolean = true,  // enabled 속성 추가
    shadowSizeRatio: Float = 0.03f, // 그림자 크기 비율
    backgroundColor: Color = Color(0xFFFF9A4D),
    baseColor: Color = Color(0xFFFF9A4D), // 버튼의 기본 색상
) {
    // 화면 너비와 높이 가져오기
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // 비율에 따른 크기 계산
    val buttonSize = screenWidth * buttonSizeRatio
    val iconSize = screenWidth * iconSizeRatio
    val textSize = (screenWidth * textSizeRatio).value.sp
    val spacing = screenHeight * spacingRatio

    // 현재 시간이 밤(19시~6시)인지 확인하여 텍스트 색상 설정
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val isNightMode = currentHour in 19..23 || currentHour in 0..6
    val textColor = if (isNightMode) Color.White else Color.Black

    // 그림자 크기 설정
    val shadowElevation = with(LocalDensity.current) {
        (screenWidth * shadowSizeRatio).toPx()
    }

    // 그라디언트 색상 계산
    val highlightColor = baseColor.copy(alpha = 1f).lighter(0.15f) // 더 밝게
    val midColor = baseColor.copy(alpha = 1f).lighter(0.1f) // 중간 색상
    val shadowColor = baseColor.copy(alpha = 1f).darker(0.1f) // 덜 어두운 색상

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(buttonSize)
                .graphicsLayer(
                    shadowElevation = shadowElevation,
                    shape = CircleShape,
                    clip = false // 그림자가 잘리지 않도록 설정
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(highlightColor, midColor, shadowColor),
                        start = Offset(0f, 0f), // 시작점 (좌측 상단)
                        end = Offset(0f, buttonSize.value) // 끝점 (아래쪽)
                    ),
                    shape = CircleShape
                ) ,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            shape = CircleShape,
            enabled = enabled
        ) {
            when {
                icon != null -> Icon(
                    imageVector = icon,
                    contentDescription = text,
                    modifier = Modifier.size(iconSize),
                    tint = Color.White
                )

                iconResId != null -> Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = text,
                    modifier = Modifier.size(iconSize)
                )
            }
        }
        Text(
            text = text,
            color = textColor,
            fontSize = textSize,
            fontWeight = FontWeight.Normal
        )
    }
}

// 확장 함수로 밝은 색상과 어두운 색상 계산
fun Color.lighter(factor: Float): Color {
    return Color(
        red = (red + factor).coerceIn(0f, 1f),
        green = (green + factor).coerceIn(0f, 1f),
        blue = (blue + factor).coerceIn(0f, 1f),
        alpha = alpha
    )
}

fun Color.darker(factor: Float): Color {
    return Color(
        red = (red - factor).coerceIn(0f, 1f),
        green = (green - factor).coerceIn(0f, 1f),
        blue = (blue - factor).coerceIn(0f, 1f),
        alpha = alpha
    )
}