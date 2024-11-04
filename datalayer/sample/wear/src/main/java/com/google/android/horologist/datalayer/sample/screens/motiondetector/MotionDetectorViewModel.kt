package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MotionDetectorViewModel @Inject constructor(
    private val sensorManager: SensorManager,
    private val context: Context
) : ViewModel() {

    private val _detectedAction = MutableStateFlow<String?>(null)
    val detectedAction: StateFlow<String?> = _detectedAction.asStateFlow()

    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private val accelerometerValues = FloatArray(3)
    private val gyroscopeValues = FloatArray(3)

    private val typingGestureDetector = TypingGestureDetector(
        onTypingGestureDetected = {
            Log.d("MotionDetectorViewModel", "Typing gesture detected.")
            updateDetectedAction("Typing gesture detected")
            // 추가 동작 처리
        }
    )

    private val eatingGestureDetector = EatingGestureDetector(
        onEatingGestureDetected = {
            Log.d("MotionDetectorViewModel", "Eating gesture detected.")
            updateDetectedAction("Eating gesture detected")
            // 추가 동작 처리
        }
    )

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                when (it.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        System.arraycopy(it.values, 0, accelerometerValues, 0, it.values.size)
                    }
                    Sensor.TYPE_GYROSCOPE -> {
                        System.arraycopy(it.values, 0, gyroscopeValues, 0, it.values.size)
                    }
                }

                // 가속도계와 자이로스코프 값이 모두 있을 때만 제스처 감지
                if (accelerometerValues.isNotEmpty() && gyroscopeValues.isNotEmpty()) {
                    typingGestureDetector.detectGesture(accelerometerValues, gyroscopeValues)
                    eatingGestureDetector.detectGesture(accelerometerValues, gyroscopeValues)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    init {
        Log.d("MotionDetectorViewModel", "Initializing MotionDetectorViewModel.")

        accelerometer?.let { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        gyroscope?.let { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }


    private fun updateDetectedAction(action: String) {
        viewModelScope.launch {
            _detectedAction.value = action
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MotionDetectorViewModel", "Unregistering sensor listener.")
        sensorManager.unregisterListener(sensorEventListener)
    }
}