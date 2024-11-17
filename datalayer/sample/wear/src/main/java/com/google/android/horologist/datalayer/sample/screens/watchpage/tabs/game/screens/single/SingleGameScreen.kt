package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.single


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Colors
import java.util.Calendar
import kotlin.system.measureTimeMillis

@Composable
fun SingleGameScreen() {
    var gameState by remember { mutableStateOf(GameState.READY) }
    var reactionTime by remember { mutableStateOf(0L) }
    var message by remember { mutableStateOf("준비되면 \n시작 버튼을 \n눌러주세요.") }
    var signalTime by remember { mutableStateOf(0L) }
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val isNightMode = currentHour in 19..23 || currentHour in 0..6
    val textColor = if (isNightMode) Color.White else Color.Black
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                if (gameState == GameState.READY) {
                    gameState = GameState.WAITING
                    message = "곧 신호가 \n나타납니다 \n기다려주세요."
                    reactionTime = 0L
                    scope.launch {
                        delay(Random.nextLong(2000, 5000)) // 2~5초 랜덤 대기 시간
                        signalTime = System.currentTimeMillis()
                        message = "지금 눌러요!"
                        gameState = GameState.SIGNAL
                    }
                } else if (gameState == GameState.SIGNAL) {
                    reactionTime = System.currentTimeMillis() - signalTime
                    message = "반응 시간: ${reactionTime}ms"
                    gameState = GameState.READY
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (gameState == GameState.SIGNAL) Color.Red else Color.Green
            )
        ) {
            Text(
                text = if (gameState == GameState.READY) "시작" else "눌러요!",
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}

enum class GameState {
    READY, WAITING, SIGNAL
}