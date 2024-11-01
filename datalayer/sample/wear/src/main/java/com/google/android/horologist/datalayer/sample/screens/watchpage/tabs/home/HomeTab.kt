// HomeTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel

@Composable
fun HomeTab(modifier: Modifier = Modifier, petViewModel: PetViewModel) {
    val petState by petViewModel.uiState.collectAsStateWithLifecycle()
    // satiety를 0-1 사이의 값으로 변환 (100 -> 1.0f, 50 -> 0.5f)
    val satietyProgress = petState.satiety / 100f
    val expProgress = petState.exp / 100f
    val name = petState.name
    val level = petState.level

    // 상태 변화 로그
    LaunchedEffect(petState.satiety) {
        println("HomeTab - Satiety Changed: ${petState.satiety}")
    }


    Box(
        modifier = Modifier.fillMaxSize() // 화면 전체를 배경으로 채움
    ) {

        // 경험치 반원들
        ExperienceArcs(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(), // fillMaxSize(0.6f)에서 변경
            leftProgress = satietyProgress,  // 왼쪽 반원의 진행률
            rightProgress = expProgress  // 오른쪽 반원의 진행률
        )

        // 캐릭터 이미지와 텍스트를 감싸는 박스
        Box(
            modifier = Modifier
                .align(Alignment.Center) // 중앙 정렬
                .offset(y = 14.dp) // 아래로 이동
        ) {
            // 캐릭터 이미지
            Image(
                painter = painterResource(id = R.drawable.dog_image), // 캐릭터 이미지 리소스
                contentDescription = "Character",
                modifier = Modifier
                    .align(Alignment.Center)
            )

            // 이미지 위에 텍스트 표시
            Text(
                text = "$name LV.$level",
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-30).dp) // 이미지 위쪽에 배치
            )
        }

    }
}

@Composable
fun ExperienceArcs(
    modifier: Modifier = Modifier,
    leftProgress: Float,
    rightProgress: Float
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 15.dp.toPx() // 선 두께
        val radius = size.minDimension / 2


        // 왼쪽 반원 (빨간색)
        drawArc(
            color = Color.Red,
            startAngle = -90f,  // 북쪽(12시)에서 시작
            sweepAngle = -(180f * leftProgress),  // 음수값: 반시계 방향으로 진행
            useCenter = false,
            topLeft = center.copy(
                x = center.x - radius,
                y = center.y - radius
            ),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
        )

        // 오른쪽 반원 (연두색)
        drawArc(
            color = Color.Green,
            startAngle = -90f,  // 북쪽(12시)에서 시작
            sweepAngle = 180f * rightProgress,  // 양수값: 시계 방향으로 진행
            useCenter = false,
            topLeft = center.copy(
                x = center.x - radius,
                y = center.y - radius
            ),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
        )
    }
}