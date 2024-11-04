package com.google.android.horologist.datalayer.sample.screens.steps.data

import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class StepsRepository @Inject constructor(
    private val sensorManager: SensorManager,
    private val sharedPreferences: SharedPreferences
) {

    // 기준일을 고려하지 않고, 걸음 센서의 누적값만 사용
    private var lastSavedStepCount: Int = -1

    fun stepsFlow(): Flow<Int> = callbackFlow {
        val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepCounterSensor == null) {
            close(Exception("Step Counter Sensor not available"))
            return@callbackFlow
        }

        println("Step sensor registered") // Sensor 등록 확인

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
                    val currentStepCount = event.values[0].toInt()
                    Log.d("StepsRepository", "Current step count emitted: $currentStepCount") // Log each emission
                    trySend(currentStepCount).isSuccess
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Optional: 센서 정확도 변경 이벤트 처리
            }
        }

        try {
            sensorManager.registerListener(listener, stepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST)
        } catch (e: Exception) {
            close(e)
        }

        // Flow가 종료될 때 마지막으로 저장한 걸음 수를 SharedPreferences에 저장
        awaitClose {
            sensorManager.unregisterListener(listener)
            sharedPreferences.edit().putInt("LAST_STEP_COUNT", lastSavedStepCount).apply()
        }
    }
}