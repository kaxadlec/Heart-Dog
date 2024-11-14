package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.ContentType
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.CustomButtonWithDot
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.ui.theme.MainColor

@Composable
fun BottomContentRow(onButtonClick: (ContentType) -> Unit, selectedContent: ContentType) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = (-48).dp) // 상단에 오프셋 적용하여 겹치기 효과
            .background(
                color = MainColor,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 버튼 Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButtonWithDot(
                    text = "ABOUT",
                    isSelected = selectedContent == ContentType.ABOUT,
                    onClick = { onButtonClick(ContentType.ABOUT) },
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                )

                CustomButtonWithDot(
                    text = "STATS",
                    isSelected = selectedContent == ContentType.STATS,
                    onClick = { onButtonClick(ContentType.STATS) },
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )
            }

            // 선택된 콘텐츠 표시
            DynamicContentDisplay(contentType = selectedContent)
        }
    }
}