package com.google.android.horologist.datalayer.sample.screens.motiondetector

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text

@Composable
fun MotionDetectorScreen(
    viewModel: MotionDetectorViewModel = hiltViewModel(),
    onSwipeUp: () -> Unit // 스와이프 동작 시 호출할 함수
) {
    val detectedAction: String? by viewModel.detectedAction.collectAsState(initial = null)

    // Modifier로 스와이프 동작 감지
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectVerticalSwipeGestures(onSwipeUp = onSwipeUp)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Motion Detector Screen")
        Spacer(modifier = Modifier.height(16.dp))

        // 감지된 동작에 따라 UI 업데이트
        if (detectedAction != null) {
            Text(text = "Detected Action: $detectedAction")
        } else {
            Text(text = "No motion detected yet.")
        }
    }
}

// 수직 스와이프 감지 확장 함수
suspend fun PointerInputScope.detectVerticalSwipeGestures(
    onSwipeUp: () -> Unit
) {
    detectDragGestures { change, dragAmount ->
        change.consume() // 제스처 이벤트 소모

        val (_, dy) = dragAmount
        if (dy < -100) { // 위로 스와이프하는 경우 (dy가 음수)
            onSwipeUp()
        }
    }
}

