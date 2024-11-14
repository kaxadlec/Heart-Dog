package com.google.android.horologist.datalayer.sample.screens.watchpage.components

import androidx.compose.foundation.Image
import androidx.wear.compose.material.Text

// core/common/ui/CircleIconButton.kt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
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
    backgroundColor: Color = Color(0xFFFF9A4D),
    textSizeRatio: Float = 0.05f,
    spacingRatio: Float = 0.02f,
    enabled: Boolean = true  // enabled 속성 추가
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(buttonSize),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor
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