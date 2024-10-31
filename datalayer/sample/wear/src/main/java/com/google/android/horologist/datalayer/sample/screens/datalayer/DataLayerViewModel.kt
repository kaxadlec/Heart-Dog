package com.google.android.horologist.datalayer.sample.screens.datalayer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpcKt.CounterServiceCoroutineStub
import com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue
import com.google.android.horologist.datalayer.sample.shared.grpc.counterDelta
import com.google.android.horologist.datalayer.sample.shared.grpc.updatedOrNull
import com.google.protobuf.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataLayerViewModel
@Inject
constructor(
    private val counterService: CounterServiceCoroutineStub,
    private val counterFlow: Flow<CounterValue>,
    private val sensorManager: SensorManager,
) : ViewModel() {

    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val _gestureCount = MutableLiveData<Int>(0)
    val gestureCount: LiveData<Int> = _gestureCount // UI에서 관찰할 수 있는 LiveData

    val uiState: MutableStateFlow<DataLayerScreenState> = MutableStateFlow(DataLayerScreenState())


    // TypingGestureDetector 인스턴스 생성
    private val TypingGestureDetector = TypingGestureDetector(

        onTypingGestureDetected = {
            Log.d("DataLayerViewModel", "'Typing' gesture detected.")
            incrementGestureCount() }
    )

    // FistGestureDetector 인스턴스 생성
    private val fistGestureDetector = FistGestureDetector(

        onFistGestureDetected = {
            Log.d("DataLayerViewModel", "Fist gesture detected.")
            incrementGestureCount() }
    )



    init {
        Log.d("DataLayerViewModel", "Initializing DataLayerViewModel.")
        accelerometer?.let { sensor ->
            Log.d("DataLayerViewModel", "Accelerometer sensor available.")
            sensorManager.registerListener(object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                        TypingGestureDetector.detectTypingGesture(event.values)
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } ?: throw Exception("Accelerometer sensor not available")

        viewModelScope.launch {
            counterFlow.collectLatest { newValue ->
                uiState.update { state ->
                    updateIfNewer(state, newValue)
                }
            }
        }
    }

    private fun incrementGestureCount() {
        val newCount = (_gestureCount.value ?: 0) + 1
        _gestureCount.value = newCount

            addDelta(1) // 1씩 증가하도록 설정
    }

    fun addDelta(i: Int) {
        viewModelScope.launch {
            try {
                val newValue: CounterValue =
                    counterService.increment(counterDelta { delta = i.toLong() })
                uiState.update {
                    updateIfNewer(it, newValue)
                }
            } catch (e: Exception) {
                uiState.update {
                    it.copy(error = e.message)
                }
            }
        }
    }

    private fun updateIfNewer(
        it: DataLayerScreenState,
        newValue: CounterValue,
    ): DataLayerScreenState {
        val currentUpdated = it.counterValue?.updatedOrNull
        return if (currentUpdated == null || currentUpdated.isBefore(newValue)) {
            it.copy(counterValue = newValue)
        } else {
            it
        }
    }

    private fun Timestamp.isBefore(other: CounterValue): Boolean =
        seconds < other.updated.seconds || (seconds == other.updated.seconds && nanos < other.updated.nanos)
}

data class DataLayerScreenState(
    val counterValue: CounterValue? = null,
    val error: String? = null,
)
