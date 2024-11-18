package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.util.Log
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class EatingGestureDetector(
    private val onEatingGestureDetected: () -> Unit
) {
    // 민감도 설정 (손을 올리고 입으로 가져가는 동작에 적합한 임계값 조정)
    private val zAxisThreshold = 8.0f        // 가속도계 Z축 임계값 (손을 올리는 동작 감지)
    private val xAxisThreshold = 4.0f        // 가속도계 X축 임계값 (손의 좌우 움직임 감지)
    private val yGyroscopeThreshold = 1.5f   // 자이로스코프 Y축 임계값 (손의 회전 감지)
    private val deltaThreshold = 1.5f        // 변화량 임계값

    private val detectionInterval = 5000L    // 5초 내에 연속 감지
    private val minConsecutiveDetections = 3 // 연속 감지 횟수 (5초 내에 3번 감지)

    private var consecutiveDetections = 0
    private var lastDetectionTime = System.currentTimeMillis()

    // 이전 센서 값 저장
    private var lastAccelerometerValues = FloatArray(3)
    private var lastGyroscopeValues = FloatArray(3)
    private var filteredAccelerometerValues = FloatArray(3)
    private var filteredGyroscopeValues = FloatArray(3)

    // 노이즈 필터 적용 (Low-pass Filter)
    private fun lowPassFilter(input: FloatArray, output: FloatArray?): FloatArray {
        val alpha = 0.8f // 노이즈 필터 강도
        if (output == null) return input
        for (i in input.indices) {
            output[i] = alpha * output[i] + (1 - alpha) * input[i]
        }
        return output
    }

    // 변화량 계산 함수
    private fun calculateDelta(newValues: FloatArray, oldValues: FloatArray): Float {
        return sqrt(
            ((newValues[0] - oldValues[0]).pow(2) +
                    (newValues[1] - oldValues[1]).pow(2) +
                    (newValues[2] - oldValues[2]).pow(2)).toDouble()
        ).toFloat()
    }

    fun detectGesture(accelerometerValues: FloatArray, gyroscopeValues: FloatArray) {
        // Low-pass Filter 적용
        val filteredAccel = lowPassFilter(accelerometerValues, filteredAccelerometerValues)
        val filteredGyro = lowPassFilter(gyroscopeValues, filteredGyroscopeValues)

        // 가속도계 Z축, X축과 자이로스코프 Y축 값 가져오기
        val zValue = filteredAccel[2]
        val xValue = abs(filteredAccel[0])
        val yRotation = abs(filteredGyro[1])

        // 변화량 계산
        val accelerationDelta = calculateDelta(filteredAccel, lastAccelerometerValues)
        val gyroscopeDelta = calculateDelta(filteredGyro, lastGyroscopeValues)


        // 이전 값 갱신
        System.arraycopy(filteredAccel, 0, lastAccelerometerValues, 0, 3)
        System.arraycopy(filteredGyro, 0, lastGyroscopeValues, 0, 3)

        // 특정 임계값을 초과하는 동작만 인식 (작은 움직임은 무시)
        if (zValue > zAxisThreshold && xValue < xAxisThreshold &&
            accelerationDelta > deltaThreshold &&
            yRotation > yGyroscopeThreshold) {

            val currentTime = System.currentTimeMillis()

            // 일정 시간 내에 연속 동작 감지 시 카운트 증가
            if (currentTime - lastDetectionTime <= detectionInterval) {
                consecutiveDetections++
                Log.d("EatingGestureDetector", "Eating movement detected. Count: $consecutiveDetections")
            } else {
                // 시간 초과 시 초기화
                consecutiveDetections = 1
            }

            lastDetectionTime = currentTime

            // 연속 감지가 특정 횟수 이상일 경우 밥 먹기 동작으로 간주
            if (consecutiveDetections >= minConsecutiveDetections) {
                triggerEatingGesture()
                resetDetection()
            }
        }
    }

    private fun triggerEatingGesture() {
        Log.d("EatingGestureDetector", "Eating gesture detected!")
        onEatingGestureDetected() // 밥 먹기 동작 감지 콜백 호출
    }

    // 감지 초기화
    private fun resetDetection() {
        consecutiveDetections = 0
        lastDetectionTime = System.currentTimeMillis()
    }
}
