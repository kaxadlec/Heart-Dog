package com.google.android.horologist.datalayer.sample.screens.datalayer

class FistGestureDetector(
    private val onFistGestureDetected: () -> Unit,
    private val fistThreshold: Float = 10.0f
) {
    private var lastFistTime = 0L
    private var fistCount = 0

    fun detectFistGesture(values: FloatArray) {
        val zAxisAcceleration = values[2]
        val currentTime = System.currentTimeMillis()

        if (zAxisAcceleration > fistThreshold) {
            if (fistCount == 0) {
                fistCount++
                lastFistTime = currentTime
            } else if (fistCount == 1 && currentTime - lastFistTime < 500) {
                fistCount++
                onFistGestureDetected()
                resetGesture()
            } else {
                resetGesture()
            }
        }
    }

    private fun resetGesture() {
        lastFistTime = 0L
        fistCount = 0
    }
}
