package com.google.android.horologist.datalayer.sample.screens.motiondetector


class HandHoldDetector(
    private val onHandHoldDetected: () -> Unit,
    private val onHandReleaseDetected: () -> Unit,
    private val correlationThreshold: Float = 0.7f
) {
    private val myAccelerationHistory = mutableListOf<Float>()
    private val partnerAccelerationHistory = mutableListOf<Float>()

    fun updateMyAcceleration(values: FloatArray) {
        myAccelerationHistory.add(values[0]) // 예를 들어 X축 가속도만 사용한다고 가정
        if (myAccelerationHistory.size > 20) myAccelerationHistory.removeAt(0)
    }

    fun updatePartnerAcceleration(values: FloatArray) {
        partnerAccelerationHistory.add(values[0]) // 예를 들어 X축 가속도만 사용한다고 가정
        if (partnerAccelerationHistory.size > 20) partnerAccelerationHistory.removeAt(0)

        checkForHandHold()
    }

    private fun checkForHandHold() {
        if (myAccelerationHistory.size == partnerAccelerationHistory.size && myAccelerationHistory.size >= 10) {
            val correlation = calculateCorrelation(myAccelerationHistory, partnerAccelerationHistory)
            if (correlation >= correlationThreshold) {
                onHandHoldDetected()
            } else {
                onHandReleaseDetected()
            }
        }
    }

    private fun calculateCorrelation(list1: List<Float>, list2: List<Float>): Float {
        val mean1 = list1.average().toFloat()
        val mean2 = list2.average().toFloat()

        var numerator = 0f
        var denominator1 = 0f
        var denominator2 = 0f

        for (i in list1.indices) {
            val diff1 = list1[i] - mean1
            val diff2 = list2[i] - mean2
            numerator += diff1 * diff2
            denominator1 += diff1 * diff1
            denominator2 += diff2 * diff2
        }

        return if (denominator1 != 0f && denominator2 != 0f) {
            numerator / (Math.sqrt((denominator1 * denominator2).toDouble()).toFloat())
        } else {
            0f
        }
    }
}
