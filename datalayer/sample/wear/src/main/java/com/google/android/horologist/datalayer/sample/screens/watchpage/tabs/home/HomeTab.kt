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
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.ExperienceArcs

@Composable
fun HomeTab(modifier: Modifier = Modifier,
            petViewModel: PetViewModel,
            userViewModel: UserViewModel = hiltViewModel(),
            ) {

    val petState by petViewModel.uiState.collectAsStateWithLifecycle()
    // satiety를 0-1 사이의 값으로 변환 (100 -> 1.0f, 50 -> 0.5f)
    val userState by userViewModel.uiState.collectAsStateWithLifecycle()
    val satietyProgress = petState.satiety / 100f
    val expProgress = petState.exp / 100f
    val name = petState.name
    val level = petState.level


    Box(
        modifier = Modifier.fillMaxSize(), // 화면 전체를 배경으로 채움
        contentAlignment = Alignment.Center
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
            if (userState.hasPet) {
                Image(
                    painter = painterResource(id = R.drawable.dog_image),
                    contentDescription = "Character",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.doghouse),
                    contentDescription = "Doghouse",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

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