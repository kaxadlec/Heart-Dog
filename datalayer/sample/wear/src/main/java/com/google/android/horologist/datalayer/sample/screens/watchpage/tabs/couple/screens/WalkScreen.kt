package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.horologist.datalayer.sample.screens.steps.StepsViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

@Composable
fun WalkScreen(stepsViewModel: StepsViewModel = hiltViewModel(),
               userViewModel: UserViewModel = hiltViewModel()) {
    val stepsState = stepsViewModel.uiState.collectAsState()
    val userState = userViewModel.uiState.collectAsState()
    val stepCount = stepsState.value.stepCountValue?.value ?: 0

    // 200보마다 하트 1개씩 증가 (제한 없음)
    LaunchedEffect(stepCount) {
        val earnedHearts = stepCount / 20
        if (earnedHearts > 0) {
            val newHeartValue = (userState.value.heart + earnedHearts).coerceAtMost(100)
            userViewModel.updateHeart(newHeartValue)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val icon: Painter = painterResource(id = R.drawable.couplewalking)
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = "함께한 발맞춤",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 걸음 수 표시
        Text(
            text = "$stepCount 걸음",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 획득한 하트 표시
        Text(
            text = "획득한 하트: ${stepCount / 20}개",
            fontSize = 14.sp,
            color = Color.Black
        )

        // 다음 하트까지 남은 걸음 수 표시
        val remainingSteps = 20 - (stepCount % 20)
        Text(
            text = "다음 하트까지 ${remainingSteps}걸음",
            fontSize = 12.sp,
            color = Color.Gray
        )

    }
}