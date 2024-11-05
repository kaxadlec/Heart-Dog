package com.google.android.horologist.datalayer.sample.screens.heartrate.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.health.services.client.data.DataTypeAvailability
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.heartrate.data.HeartRateServicesRepository
import com.google.android.horologist.datalayer.sample.screens.heartrate.data.MeasureMessage
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateProto.HeartRateRecord
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateServiceGrpcKt.HeartRateServiceCoroutineStub
import com.google.android.horologist.datalayer.sample.shared.grpc.heartRateDelta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeartRateViewModel
    @Inject
    constructor(
        private val heartRateService: HeartRateServiceCoroutineStub,
        private val heartRateFlow: Flow<HeartRateRecord>,
        private val heartRateServicesRepository: HeartRateServicesRepository
    ) : ViewModel() {

    private val _hr = MutableStateFlow(0.0)
    val hr: StateFlow<Double> = _hr.asStateFlow()
//    val hr: MutableState<Double> = mutableStateOf(0.0)

    private val _maxHeartRate = MutableStateFlow(0.0)
    val maxHeartRate: StateFlow<Double> = _maxHeartRate.asStateFlow()

//    val availability: MutableState<DataTypeAvailability> =
//        mutableStateOf(DataTypeAvailability.UNKNOWN)
    private val _availability = MutableStateFlow(DataTypeAvailability.UNKNOWN)
    val availability: StateFlow<DataTypeAvailability> = _availability.asStateFlow()

    val enabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

//    val uiState: MutableState<UiState> = mutableStateOf(UiState.Startup)
    private val _uiState = MutableStateFlow<UiState>(UiState.Startup)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val supported = heartRateServicesRepository.hasHeartRateCapability()
            _uiState.value = if (supported) {
                UiState.Supported
            } else {
                UiState.NotSupported
            }
        }

        viewModelScope.launch {
            enabled.collect {
                if (it) {
                    heartRateServicesRepository.heartRateMeasureFlow()
                        .takeWhile { enabled.value }
                        .collect { measureMessage ->
                            when (measureMessage) {
                                is MeasureMessage.MeasureData -> {
//                                    hr.value = measureMessage.data.last().value
                                    _hr.value = measureMessage.data.last().value

                                    /* update data */
                                    Log.i("heartRate", "measure start ${hr.value.toInt()}")

                                    /* Coroutine */
                                    val newRecord = heartRateService.put(heartRateDelta { heartRate = hr.value.toInt() })
                                    Log.i("heartRate", "measure end ${newRecord.heartRate}")
                                }
                                is MeasureMessage.MeasureAvailability -> {
                                    _availability.value = measureMessage.availability
                                }
                            }
                        }
                }
            }
        }
    }

    fun toggleEnabled() {
        enabled.value = !enabled.value
        if (!enabled.value) {
            _availability.value = DataTypeAvailability.UNKNOWN
        }
    }

    fun saveMaxHeartRate(value: Double) {
        _maxHeartRate.value = value
        viewModelScope.launch {
            try {
                heartRateService.put(heartRateDelta {
                    heartRate = value.toInt()
                })
                Log.d("HeartRateViewModel", "Successfully saved heart rate to service") // 로그 추가
            } catch (e: Exception) {
                Log.e("HeartRateViewModel", "Failed to save heart rate", e)
            }
        }
    }

}

//class HeartRateViewModelFactory(
//    private val heartRateServicesRepository: HeartRateServicesRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HeartRateViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return HeartRateViewModel(
//                heartRateServicesRepository = heartRateServicesRepository
//            ) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

sealed class UiState {
    object Startup : UiState()
    object NotSupported : UiState()
    object Supported : UiState()
}