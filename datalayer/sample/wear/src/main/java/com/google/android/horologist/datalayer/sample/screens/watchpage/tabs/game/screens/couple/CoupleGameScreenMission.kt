package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun CoupleGameScreenMission(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
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
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "커플 랜덤 미션",
            fontSize = 10.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = randomActivity,
            fontSize = 13.sp,
            color = Color.Black
        )
        Text(
            text = "${timeLeft}초",
            fontSize = 10.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onBack) {
            Text("<",
                fontSize = 10.sp)
        }
    }
}
