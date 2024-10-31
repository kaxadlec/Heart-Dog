package com.google.android.horologist.datalayer.sample.screens.datalayer

class TypingGestureDetector(
    private val onTypingGestureDetected: () -> Unit,
    private val typingThreshold: Float = 0.5f,       // 감도 조절 값, 미세한 움직임 감지
    private val minTypingCount: Int = 8,             // 타자 감지 기준 횟수
    private val detectionTimeWindow: Long = 10_000L  // 10초 내에 감지
) {
    private var lastMovementTime = 0L
    private var typingCount = 0
    private var lastAcceleration = 0f

    fun detectTypingGesture(values: FloatArray) {
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

        // 미세한 가속도 변화 감지
        if (accelerationChange in typingThreshold..2.0f) { // 작지만 명확한 변화
            typingCount++

            // 첫 감지 시점 기록
            if (typingCount == 1) {
                lastMovementTime = currentTime
            }

            // 10초 내에 minTypingCount 이상 감지되면 타자치는 동작으로 인식
            if (currentTime - lastMovementTime <= detectionTimeWindow && typingCount >= minTypingCount) {
                onTypingGestureDetected()
                resetGesture()
            }
        } else if (currentTime - lastMovementTime > detectionTimeWindow) {
            // 10초 경과 시 초기화
            resetGesture()
        }

        lastAcceleration = totalAcceleration // 현재 가속도를 이전 값으로 업데이트
    }

    private fun resetGesture() {
        lastMovementTime = 0L
        typingCount = 0
        lastAcceleration = 0f
    }
}
