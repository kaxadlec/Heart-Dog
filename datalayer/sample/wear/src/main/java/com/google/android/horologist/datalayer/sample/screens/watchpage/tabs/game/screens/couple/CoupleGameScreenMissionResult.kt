package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple

import android.util.Log
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.Screen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.GameTabScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

@Composable
fun CoupleGameScreenMissionResult(
    onBack: () -> Unit,
    navController: NavController,
    heartRateViewModel: HeartRateViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val DarkRed = Color(0xFFB00020)
    // maxHeartRate StateFlow 수집
    val maxHeartRate by heartRateViewModel.maxHeartRate.collectAsState(initial = 0.0) // 최대 심박수
    // 최대 심박수에 따른 하트 획득량 계산 (일단 기준 90으로 설정)
    val heartsEarned = (maxHeartRate - 90).coerceAtLeast(0.0).toInt() // 최대 심박수가 90을 넘어야 하트 획득
    // UserViewModel의 StateFlow 수집
    val userState by userViewModel.uiState.collectAsState()

    // 값 확인을 위한 로그
    LaunchedEffect(maxHeartRate) {
        Log.d("CoupleGameResult", "Current max heart rate: $maxHeartRate")
        Log.d("CoupleGameResult", "Hearts earned: $heartsEarned")

        // 현재 하트 + 획득한 하트로 업데이트
        val newHeartCount = userState.heart + heartsEarned
        userViewModel.updateHeart(newHeartCount)
        Log.d("CoupleGameResult", "Updated heart count: $newHeartCount")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "당신의 최고 심박수 : ${maxHeartRate.toInt()} bpm",
            fontSize = 14.sp,
            color = Color.Black
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "획득한 하트: $heartsEarned",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkRed
        )


        Spacer(modifier = Modifier.height(12.dp))

        androidx.wear.compose.material.Button(
            onClick = {
                // 게임화면 밖으로 나가기
                navController.popBackStack(
                    route = GameTabScreen.Main.route,
                    inclusive = false
                )
            },
            modifier = Modifier
                .size(width = 80.dp, height = 30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF9A4D),
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "확인",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

