package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
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
import com.google.android.horologist.datalayer.sample.SampleApplication
import java.util.Calendar

@Composable
fun CoupleGameScreen(
    initialTime: Int = 5,
    missionDuration: Int = 10,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate: (Float) -> Unit // maxHeartRate 전달
) {
    val application = LocalContext.current.applicationContext as SampleApplication
    val currentHr by application.heartRate.collectAsState(initial = 0.0)

    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val isNightMode = currentHour in 19..23 || currentHour in 0..6
    val textColor = if (isNightMode) Color.White else Color.Black
    val DarkRed = Color(0xFFB00020)

    var timeLeft by remember { mutableStateOf(initialTime) }
    var missionTimeLeft by remember { mutableStateOf(missionDuration) }
    var isMissionStarted by remember { mutableStateOf(false) }
    val activities = listOf(
        "서로 눈맞춤",
        //        "마주 보고 손잡기",
        //        "서로에게 사랑한다고 말하기",
        //        "서로 볼 마주 대고 있기",
        //        "하이파이브 반복하기",
        //        "어깨동무하고 셀카찍기"
    )
    val randomActivity = remember { activities[Random.nextInt(activities.size)] }

    var maxHeartRate by remember { mutableStateOf(0.0) }

    LaunchedEffect(currentHr) {
        if (currentHr > maxHeartRate) {
            maxHeartRate = currentHr
            Log.d("CoupleGameScreen", "최고 심박수 갱신: $maxHeartRate")
        }
    }

    // 준비 타이머
    LaunchedEffect(timeLeft) {
        if (!isMissionStarted && timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else if (timeLeft == 0) {
            isMissionStarted = true
        }
    }

    // 미션 타이머
    LaunchedEffect(isMissionStarted, missionTimeLeft) {
        if (isMissionStarted && missionTimeLeft > 0) {
            delay(1000L)
            missionTimeLeft -= 1
        } else if (missionTimeLeft == 0) {
            onNavigate(maxHeartRate.toFloat())
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isMissionStarted) {
            // 준비 타이머 화면
            Text(
                text = "READY",
                fontSize = 32.sp, // 글씨 크기 증가
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "${timeLeft} 초",
                fontSize = 28.sp, // 글씨 크기 증가
                color = textColor
            )
        } else {
            // 미션 화면
            Text(
                text = "커플 랜덤 미션",
                fontSize = 28.sp, // 글씨 크기 증가
                color = textColor,
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = randomActivity,
                fontSize = 30.sp, // 글씨 크기 증가
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "${missionTimeLeft}초",
                fontSize = 26.sp, // 글씨 크기 증가
                color = textColor,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                val icon: Painter = painterResource(id = R.drawable.redheart)
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp) // 이미지 크기 증가
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = currentHr.toInt().toString(),
                    fontSize = 28.sp, // 글씨 크기 증가
                    color = DarkRed
                )
            }
        }

    }
}
