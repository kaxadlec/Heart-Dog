package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.Navigator
import androidx.wear.compose.material.Colors
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun CoupleGameScreen(
    initialTime: Int = 3,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate : () -> Unit
) {
    val timeLeft = remember { mutableStateOf(initialTime) }
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val isNightMode = currentHour in 19..23 || currentHour in 0..6
    val textColor = if (isNightMode) Color.White else Color.Black

    LaunchedEffect(timeLeft.value) {
        if (timeLeft.value > 0) {
            delay(1000L)
            timeLeft.value -= 1
        }
        else {
            onNavigate()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("READY",
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "${timeLeft.value} 초",
            fontSize = 24.sp,
            color = textColor
        )
    }
}