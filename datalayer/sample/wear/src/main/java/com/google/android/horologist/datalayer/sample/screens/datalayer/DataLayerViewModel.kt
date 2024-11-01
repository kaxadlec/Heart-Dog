package com.google.android.horologist.datalayer.sample.screens.datalayer

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
) : ViewModel() {
    init {
        viewModelScope.launch {
            counterFlow.collectLatest { newValue ->
                uiState.update { state ->
                    updateIfNewer(state, newValue)
                }
            }
        }
    }

    val uiState: MutableStateFlow<DataLayerScreenState> = MutableStateFlow(DataLayerScreenState())

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
}

private fun Timestamp.isBefore(other: CounterValue): Boolean =
    seconds < other.updated.seconds || (seconds == other.updated.seconds && nanos < other.updated.nanos)

data class DataLayerScreenState(
    val counterValue: CounterValue? = null,
    val error: String? = null,
)
