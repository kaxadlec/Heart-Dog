package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.R

@Composable
fun TopContentBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.49f), // 화면의 50%를 세로로 차지
        contentAlignment = Alignment.TopCenter // 텍스트를 위쪽 중앙에 배치
    ) {
        // 배경 이미지 설정
        Image(
            painter = painterResource(R.drawable.mtbg),
            contentDescription = null, // 장식용 이미지이므로 contentDescription은 null로 설정
            modifier = Modifier
                .fillMaxSize() // Box 크기에 맞게 이미지 채움
        )

        // hotdog 타이틀 추가
        Image(
            painter = painterResource(R.drawable.title),
            contentDescription = null, // 장식용 이미지이므로 contentDescription은 null로 설정
            modifier = Modifier
                .align(Alignment.TopCenter) // 중앙에 위치시킴
                .width(200.dp) // 양옆으로만 크기를 줄임
                .padding(top = 16.dp) // 위쪽에 약간의 마진 추가
                .wrapContentHeight() // 높이는 원래 비율 유지
        )

        // hotdog 이미지 추가
        Image(
            painter = painterResource(R.drawable.hotdog),
            contentDescription = null, // 장식용 이미지이므로 contentDescription은 null로 설정
            modifier = Modifier
                .align(Alignment.Center) // 중앙에 위치시킴
        )
    }
}
