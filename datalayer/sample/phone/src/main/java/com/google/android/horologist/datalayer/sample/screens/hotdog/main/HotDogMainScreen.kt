package com.google.android.horologist.datalayer.sample.screens.hotdog.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.TopContentBox
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.BottomContentRow
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.ContentType
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel

@Composable
fun HotDogMainScreen(navController: NavController, dogViewModel: DogViewModel) {
    var selectedContent by remember { mutableStateOf(ContentType.ABOUT) } // 선택된 콘텐츠 상태 관리

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopContentBox()
        BottomContentRow(
            dogViewModel = dogViewModel,
            onButtonClick = { content -> selectedContent = content }, // onButtonClick 전달
            selectedContent = selectedContent
        )
    }
    ButtonFooter(navController = navController)
}
