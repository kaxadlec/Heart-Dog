package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.screens.guide

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color

@Composable
fun GuideScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),  // 스크롤 가능하게 설정
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "사용방법 안내",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "포만도와 경험치 규칙 \n예를 들어:\n\n" +
                    "1. 첫 번째 단계 \n: 앱을 열고 설정 페이지로 이동하세요.\n" +
                    "2. 두 번째 단계 \n: '사용방법' 버튼을 클릭하여 사용 설명서를 확인합니다.\n" +
                    "3. 세 번째 단계 \n: 설정 옵션을 원하는 대로 조정합니다.\n" +
                    "4. 추가 정보 \n: 더 많은 도움말을 보려면 고객 지원에 문의하세요.\n\n" +
                    "이 앱을 통해 다양한 기능을 탐색하고 최적의 사용자 경험을 즐기세요!",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

//        Button(onClick = onBack) {
//            Text("뒤로가기")
//        }
    }
}
