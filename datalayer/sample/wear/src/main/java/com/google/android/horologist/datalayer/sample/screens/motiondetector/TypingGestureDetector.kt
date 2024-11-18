package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.util.Log
import kotlin.math.abs

class TypingGestureDetector(
    private val onTypingGestureDetected: () -> Unit
) {
    // 민감도 설정 (타이핑 동작 감지)
    private val zAxisThreshold = 1.0f       // Z축 가속도 민감도 (손가락의 빠른 위아래 움직임)
    private val yGyroThreshold = 0.8f       // Y축 자이로스코프 민감도 (손가락의 빠른 회전)
    private val detectionWindow = 1000L     // 1초 내에 연속 감지
    private val minConsecutiveDetections = 5 // 연속 감지 횟수

    private var consecutiveDetections = 0
    private var lastDetectionTime = System.currentTimeMillis()

    // 이전 센서 값 저장
    private var lastZAccel = 0f
    private var lastYGyro = 0f

    fun detectGesture(accelerometerValues: FloatArray, gyroscopeValues: FloatArray) {
        // Z축 가속도 값 가져오기 (손가락의 위아래 움직임)
        val zAccel = accelerometerValues[2]

        // Y축 자이로스코프 값 가져오기 (빠른 회전 감지)
        val yGyro = gyroscopeValues[1]

        // Z축 가속도 및 Y축 자이로스코프 변화량 계산
        val zDelta = abs(zAccel - lastZAccel)
        val yGyroDelta = abs(yGyro - lastYGyro)

        // 이전 값 업데이트
        lastZAccel = zAccel
        lastYGyro = yGyro

        // 타이핑 동작 감지 조건: Z축과 Y축이 특정 임계값을 초과할 경우
        if (zDelta > zAxisThreshold && yGyroDelta > yGyroThreshold) {
            val currentTime = System.currentTimeMillis()

            // 일정 시간 내에 연속 동작 감지 시 카운트 증가
            if (currentTime - lastDetectionTime <= detectionWindow) {
                consecutiveDetections++
                Log.d("TypingGestureDetector", "Typing detected. Count: $consecutiveDetections")
            } else {
                consecutiveDetections = 1
            }

            lastDetectionTime = currentTime

            // 연속 감지가 특정 횟수 이상일 경우 타이핑으로 간주
            if (consecutiveDetections >= minConsecutiveDetections) {
                triggerTypingGesture()
                consecutiveDetections = 0
            }
        }
    }

    private fun triggerTypingGesture() {
        Log.d("TypingGestureDetector", "Typing gesture detected!")
        onTypingGestureDetected()
    }
}
