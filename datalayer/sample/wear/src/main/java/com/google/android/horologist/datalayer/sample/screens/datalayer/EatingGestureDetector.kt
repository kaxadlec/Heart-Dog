package com.google.android.horologist.datalayer.sample.screens.datalayer

class EatingGestureDetector(
    private val onEatingGestureDetected: () -> Unit,
    private val eatingThreshold: Float = 1.5f,        // 감도 조절 값, 큰 움직임 감지
    private val minEatingCount: Int = 3,              // 숟가락질 감지 기준 횟수
    private val detectionTimeWindow: Long = 20_000L   // 20초 내에 감지
) {
    private var lastMovementTime = 0L
    private var eatingCount = 0
    private var lastAcceleration = 0f

    fun detectEatingGesture(values: FloatArray) {
        val xAxisAcceleration = values[0]
        val yAxisAcceleration = values[1]
        val zAxisAcceleration = values[2]

        // 총 가속도 벡터 크기 계산
        val totalAcceleration = Math.sqrt(
            (xAxisAcceleration * xAxisAcceleration +
                    yAxisAcceleration * yAxisAcceleration +
                    zAxisAcceleration * zAxisAcceleration).toDouble()
        ).toFloat()

        val currentTime = System.currentTimeMillis()
        val accelerationChange = Math.abs(totalAcceleration - lastAcceleration)

        // 큰 가속도 변화 감지 (밥 먹는 동작은 비교적 큰 움직임)
        if (accelerationChange in eatingThreshold..3.0f) { // 더 큰 변화 허용
            eatingCount++

            // 첫 감지 시점 기록
            if (eatingCount == 1) {
                lastMovementTime = currentTime
            }

            // 20초 내에 minEatingCount 이상 감지되면 밥 먹는 동작으로 인식
            if (currentTime - lastMovementTime <= detectionTimeWindow && eatingCount >= minEatingCount) {
                onEatingGestureDetected()
                resetGesture()
            }
        } else if (currentTime - lastMovementTime > detectionTimeWindow) {
            // 20초 경과 시 초기화
            resetGesture()
        }

        lastAcceleration = totalAcceleration // 현재 가속도를 이전 값으로 업데이트
    }

    private fun resetGesture() {
        lastMovementTime = 0L
        eatingCount = 0
        lastAcceleration = 0f
    }
}
