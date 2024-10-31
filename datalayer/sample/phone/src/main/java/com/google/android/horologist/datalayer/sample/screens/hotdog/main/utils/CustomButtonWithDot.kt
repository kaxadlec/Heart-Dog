package com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButtonWithDot(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 버튼 텍스트
        CustomButton(
            text = text,
            isSelected = isSelected,
            onClick = onClick
        )

        // 버튼 아래 점을 선택된 상태에서만 표시
        if (isSelected) {
            val dotStyle = buttonTextStyle(isSelected)
            Text(
                text = "•", // 큰 점
                fontSize = 36.sp, // 점 크기 2배
                color = dotStyle.color,
                modifier = Modifier.offset(y = (-8).dp)
            )
        }
    }
}
