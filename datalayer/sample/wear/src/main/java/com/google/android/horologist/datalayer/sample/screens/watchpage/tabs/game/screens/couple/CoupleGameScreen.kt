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
import kotlinx.coroutines.delay

@Composable
fun CoupleGameScreen(
    initialTime: Int = 3,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate : () -> Unit
) {
    val timeLeft = remember { mutableStateOf(initialTime) }

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
            modifier = Modifier.padding(16.dp)
        )

//        Button(onClick = onBack) {
//            Text("<")
//        }
        Text(
            text = "${timeLeft.value} 초",
            fontSize = 24.sp,
        )
    }
}