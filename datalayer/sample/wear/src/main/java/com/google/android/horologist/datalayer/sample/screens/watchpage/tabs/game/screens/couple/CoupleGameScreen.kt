//package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.Alignment
//import androidx.wear.compose.material.Button
//import androidx.wear.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.Navigator
//import androidx.wear.compose.material.Colors
//import kotlinx.coroutines.delay
//import java.util.Calendar
//
//@Composable
//fun CoupleGameScreen(
//    initialTime: Int = 3,
//    modifier: Modifier = Modifier,
//    onBack: () -> Unit,
//    onNavigate : () -> Unit
//) {
//    val timeLeft = remember { mutableStateOf(initialTime) }
//    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
//    val isNightMode = currentHour in 19..23 || currentHour in 0..6
//    val textColor = if (isNightMode) Color.White else Color.Black
//
//    LaunchedEffect(timeLeft.value) {
//        if (timeLeft.value > 0) {
//            delay(1000L)
//            timeLeft.value -= 1
//        }
//        else {
//            onNavigate()
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("READY",
//            fontSize = 20.sp,
//            color = textColor,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        Text(
//            text = "${timeLeft.value} 초",
//            fontSize = 24.sp,
//            color = textColor
//        )
//    }
//}

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
import java.util.Calendar

@Composable
fun CoupleGameScreen(
    initialTime: Int = 15,
    missionDuration: Int = 15,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    heartRateViewModel: HeartRateViewModel = hiltViewModel()
) {
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val isNightMode = currentHour in 19..23 || currentHour in 0..6
    val textColor = if (isNightMode) Color.White else Color.Black
    val DarkRed = Color(0xFFB00020)

    // States
    var timeLeft by remember { mutableStateOf(initialTime) }
    var missionTimeLeft by remember { mutableStateOf(missionDuration) }
    var isMissionStarted by remember { mutableStateOf(false) }
    val activities = listOf(
        "서로 눈맞춤",
        "마주 보고 손잡기",
        "서로에게 사랑한다고 말하기",
        "서로 볼 마주 대고 있기",
        "하이파이브 반복하기",
        "어깨동무하고 셀카찍기"
    )
    val randomActivity = remember { activities[Random.nextInt(activities.size)] }

    var maxHeartRate by remember { mutableStateOf(0.0) }
    val currentHr by heartRateViewModel.hr.collectAsState(initial = 0.0)

    // Game start and heart rate monitoring
    LaunchedEffect(Unit) {
        heartRateViewModel.toggleEnabled()
    }

    // Unified timer handling
    LaunchedEffect(isMissionStarted, timeLeft, missionTimeLeft) {
        if (!isMissionStarted) {
            if (timeLeft > 0) {
                delay(1000L)
                timeLeft -= 1
            } else {
                isMissionStarted = true
            }
        } else {
            while (missionTimeLeft > 0) {
                delay(1000L)
                missionTimeLeft -= 1
            }
            heartRateViewModel.saveMaxHeartRate(maxHeartRate)
            heartRateViewModel.toggleEnabled()
            onNavigate()
        }
    }

    // Update max heart rate
    LaunchedEffect(currentHr) {
        if (currentHr > maxHeartRate) {
            maxHeartRate = currentHr
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
        if (!isMissionStarted) {
            // Initial countdown screen
            Text(
                text = "READY",
                fontSize = 20.sp,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "${timeLeft} 초",
                fontSize = 24.sp,
                color = textColor
            )
        } else {
            // Mission screen
            Text(
                text = "커플 랜덤 미션",
                fontSize = 12.sp,
                color = textColor,
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = randomActivity,
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "${missionTimeLeft}초",
                fontSize = 18.sp,
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
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = currentHr.toInt().toString(),
                    fontSize = 20.sp,
                    color = DarkRed
                )
            }
        }
    }
}