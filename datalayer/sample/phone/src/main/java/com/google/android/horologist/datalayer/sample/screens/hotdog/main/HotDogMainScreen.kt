package com.google.android.horologist.datalayer.sample.screens.hotdog.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.TopContentBox
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.BottomContentRow
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.ContentType

@Composable
fun HotDogMainScreen() {
    var selectedContent by remember { mutableStateOf(ContentType.ABOUT) } // 선택된 콘텐츠 상태 관리

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopContentBox()
        BottomContentRow(
            onButtonClick = { content -> selectedContent = content }, // onButtonClick 전달
            selectedContent = selectedContent
        )
    }
}
