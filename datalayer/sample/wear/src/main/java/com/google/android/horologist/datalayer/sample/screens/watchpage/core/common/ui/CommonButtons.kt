package com.google.android.horologist.datalayer.sample.screens.watchpage.core.common.ui

import androidx.compose.foundation.Image
import androidx.wear.compose.material.Text

// core/common/ui/CommonButtons.kt

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon


@Composable
fun CircleIconButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconResId: Int? = null,
//    buttonSize: Dp = 55.dp,
//    iconSize: Dp = 24.dp,
    buttonSizeRatio: Float = 0.3f,  // 화면 너비 대비 버튼 크기 비율
    iconSizeRatio: Float = 0.14f,  //
    backgroundColor: Color = Color(0xFFC56013),
    textColor: Color = Color.Black,
//    textSize: TextUnit = 10.sp,
    textSizeRatio: Float = 0.05f,
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.5.dp)
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
                    modifier = Modifier.size(iconSize)
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
            fontWeight = FontWeight.Bold
        )
    }
}