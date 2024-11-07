package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random
import com.google.android.horologist.datalayer.sample.R

@Composable
fun CoupleGameScreenMission(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    heartRateViewModel: HeartRateViewModel = hiltViewModel()
) {
    val activities = listOf(
        "서로 눈맞춤",
        "마주 보고 손잡기",
        "서로에게 사랑한다고 말하기",
        "서로 볼 마주 대고 있기",
        "하이파이브 반복하기",
        "어깨동무하고 셀카찍기"
    )

    val randomActivity = remember { activities[Random.nextInt(activities.size)] }
    var timeLeft by remember { mutableStateOf(10) }
    var maxHeartRate by remember { mutableStateOf(0.0) }
    // StateFlow 수집
    val currentHr by heartRateViewModel.hr.collectAsState(initial = 0.0)
    val DarkRed = Color(0xFFB00020)


    // 게임 시작 시 심박수 측정 시작
    LaunchedEffect(Unit) {
        heartRateViewModel.toggleEnabled()
    }

    // 심박수 모니터링 및 최대값 갱신
    LaunchedEffect(currentHr) {
        if (currentHr > maxHeartRate) {
            maxHeartRate = currentHr
            Log.d("CoupleGame", "새로운 최대 심박수: $maxHeartRate") // 로그 추가
        }
    }

    // 타이머 및 게임 종료 처리
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else {
            Log.d("CoupleGame", "최종 최대 심박수 저장: $maxHeartRate") //
            heartRateViewModel.saveMaxHeartRate(maxHeartRate)
            heartRateViewModel.toggleEnabled()
            onNavigate()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            heartRateViewModel.toggleEnabled()
        }
    }


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "커플 랜덤 미션",
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.padding(6.dp)
        )

        Text(
            text = randomActivity,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "${timeLeft}초",
            fontSize = 18.sp,
            color = Color.Black
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            val icon: Painter = painterResource(id = R.drawable.redheart)
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between the icon and text
            Text(
                text = currentHr.toInt().toString(),
                fontSize = 20.sp,
                color = DarkRed
            )
        }



//        Button(onClick = onBack) {
//            Text("<",
//                fontSize = 10.sp)
//        }
    }
}
