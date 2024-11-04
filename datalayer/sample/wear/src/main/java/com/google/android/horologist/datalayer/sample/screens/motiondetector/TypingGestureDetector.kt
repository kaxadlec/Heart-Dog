package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.util.Log
import kotlin.math.sqrt

class TypingGestureDetector(
    private val onTypingGestureDetected: () -> Unit
) {
    private val accelerometerThreshold = 2.0f   // 가속도계 민감도 설정
    private val gyroscopeThreshold = 2.0f       // 자이로스코프 민감도 설정
    private val typingPatternDuration = 60000     // 타이핑 동작 지속 시간 (밀리초)

    private var lastTypingTime = System.currentTimeMillis()

    fun detectGesture(accelerometerValues: FloatArray, gyroscopeValues: FloatArray) {
        // 가속도계 값의 크기 계산
        val accelerationMagnitude = sqrt(
            (accelerometerValues[0] * accelerometerValues[0] +
                    accelerometerValues[1] * accelerometerValues[1] +
                    accelerometerValues[2] * accelerometerValues[2]).toDouble()
        ).toFloat()

        // 자이로스코프 값의 크기 계산
        val gyroscopeMagnitude = sqrt(
            (gyroscopeValues[0] * gyroscopeValues[0] +
                    gyroscopeValues[1] * gyroscopeValues[1] +
                    gyroscopeValues[2] * gyroscopeValues[2]).toDouble()
        ).toFloat()

        // 가속도계와 자이로스코프의 민감도 기준을 모두 초과할 경우 타이핑으로 간주
        if (accelerationMagnitude > accelerometerThreshold && gyroscopeMagnitude > gyroscopeThreshold) {
            triggerTypingGesture()
        }
    }

    private fun triggerTypingGesture() {
        val currentTime = System.currentTimeMillis()
        // 중복 감지를 방지하기 위해 설정된 지속 시간 이후에만 감지
        if (currentTime - lastTypingTime > typingPatternDuration) {
            lastTypingTime = currentTime
            Log.d("TypingGestureDetector", "Typing gesture detected!")
            onTypingGestureDetected()  // 타이핑 동작 감지 콜백 호출
        }
    }
}
