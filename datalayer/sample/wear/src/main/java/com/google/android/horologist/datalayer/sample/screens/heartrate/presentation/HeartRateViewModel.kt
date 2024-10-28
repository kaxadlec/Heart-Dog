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
    val enabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val hr: MutableState<Double> = mutableStateOf(0.0)
    val availability: MutableState<DataTypeAvailability> =
        mutableStateOf(DataTypeAvailability.UNKNOWN)

    val uiState: MutableState<UiState> = mutableStateOf(UiState.Startup)

    init {
        viewModelScope.launch {
            val supported = heartRateServicesRepository.hasHeartRateCapability()
            uiState.value = if (supported) {
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
                                    hr.value = measureMessage.data.last().value

                                    /* update data */
                                    Log.i("heartRate", "measure start ${hr.value.toInt()}")

                                    /* Coroutine */
                                    launch {
                                        val newRecord = heartRateService.put(heartRateDelta { heartRate = hr.value.toInt() })
                                        Log.i("heartRate", "measure end ${newRecord.heartRate}")
                                    }
                                }
                                is MeasureMessage.MeasureAvailability -> {
                                    availability.value = measureMessage.availability
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
            availability.value = DataTypeAvailability.UNKNOWN
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