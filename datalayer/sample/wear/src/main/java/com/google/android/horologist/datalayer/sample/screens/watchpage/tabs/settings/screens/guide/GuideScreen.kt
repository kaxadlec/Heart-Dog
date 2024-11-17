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
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import java.util.Calendar

@Composable
fun GuideScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val isNightMode = currentHour in 19..23 || currentHour in 0..6
    val textColor = if (isNightMode) Color.White else Color.Black
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
            color = textColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "포만도와 경험치 규칙 \n예를 들어:\n\n" +
                    "1. 포만도 \n: 포만도는 총 네 개의 단계로 구성되어 있다.\n" +
                    "2. 포만도 단계 \n: 총 30으로 설정되어 있으며, 다섯개의 하트를 먹이면 캐릭터가 변화한다.\n" +
                    "3. 경헙치 \n: 커플 활동 및 포만도 증가에 따라 캐릭터가 레벨업하여 성장한다.\n" +
                    "4. 경험치 단계 \n: 경험치는 어떻게 증가하는지 나 모르겠는데?\n\n" +
                    "이 앱을 통해 커플 관계를 개선하고, 함께 강아지를 키워보세요!",
            fontSize = 14.sp,
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

//        Button(onClick = onBack) {
//            Text("뒤로가기")
//        }
    }
}
