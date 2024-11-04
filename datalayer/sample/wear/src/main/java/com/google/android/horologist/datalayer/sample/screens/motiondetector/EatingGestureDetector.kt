package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.util.Log
import kotlin.math.sqrt

class EatingGestureDetector(
    private val onEatingGestureDetected: () -> Unit
) {
    private val accelerometerThreshold = 1.8f   // 가속도계 민감도 설정
    private val gyroscopeThreshold = 0.4f       // 자이로스코프 민감도 설정
    private val eatingPatternDuration = 60000       // 밥 먹기 동작 지속 시간 (밀리초)

    private var lastEatingTime = System.currentTimeMillis()

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

        // 가속도계와 자이로스코프의 민감도 기준을 모두 초과할 경우 밥 먹기 동작으로 간주
        if (accelerationMagnitude > accelerometerThreshold && gyroscopeMagnitude > gyroscopeThreshold) {
            triggerEatingGesture()
        }
    }

    private fun triggerEatingGesture() {
        val currentTime = System.currentTimeMillis()
        // 중복 감지를 방지하기 위해 설정된 지속 시간 이후에만 감지
        if (currentTime - lastEatingTime > eatingPatternDuration) {
            lastEatingTime = currentTime
            Log.d("EatingGestureDetector", "Eating gesture detected!")
            onEatingGestureDetected()  // 밥 먹기 동작 감지 콜백 호출
        }
    }
}
