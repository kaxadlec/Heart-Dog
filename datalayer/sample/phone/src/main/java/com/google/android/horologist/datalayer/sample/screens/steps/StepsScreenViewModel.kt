package com.google.android.horologist.datalayer.sample.screens.steps

import android.util.Log
import androidx.annotation.MainThread
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountProto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepsScreenViewModel @Inject constructor(
    private val phoneDataLayerAppHelper: PhoneDataLayerAppHelper,
    private val registry: WearDataLayerRegistry,
) : ViewModel() {

    private var initializeCalled = false
    private val apiAvailable = MutableStateFlow(false)

    private lateinit var stepCountDataStore: DataStore<StepCountProto.StepCountValue>

    @OptIn(ExperimentalCoroutinesApi::class)
    private val stepCountState: Flow<StepCountProto.StepCountValue?> =
        apiAvailable.flatMapLatest { apiAvailable ->
            if (!apiAvailable) {
                flowOf(null)
            } else {
                stepCountDataStore.data
            }
        }

    private val _uiState = MutableStateFlow<StepsScreenUiState>(StepsScreenUiState.Idle)
    val uiState: StateFlow<StepsScreenUiState> = _uiState

    @MainThread
    fun initialize() {
        if (initializeCalled) return
        initializeCalled = true
        Log.d("PhoneStepViewModel", "initialize() called")

        viewModelScope.launch {
            _uiState.value = StepsScreenUiState.CheckingApiAvailability
            if (!phoneDataLayerAppHelper.isAvailable()) {
                Log.d("PhoneStepViewModel", "API not available")
                _uiState.value = StepsScreenUiState.ApiNotAvailable
            } else {
                apiAvailable.value = true
                stepCountDataStore = registry.protoDataStore(viewModelScope)
                _uiState.value = StepsScreenUiState.Loading
            }

            stepCountState.collectLatest { stepCountValue ->
                if (stepCountValue != null) {
                    Log.d("StepsScreenViewModel", "Real-time step count update received: ${stepCountValue.value}")
                    _uiState.value = StepsScreenUiState.Loaded(stepCountValue.value)
                } else {
                    Log.d("StepsScreenViewModel", "Step count update received: null")
                }
            }
        }
    }

    sealed class StepsScreenUiState {
        object Idle : StepsScreenUiState()
        object Loading : StepsScreenUiState()
        object CheckingApiAvailability : StepsScreenUiState()
        object ApiNotAvailable : StepsScreenUiState()
        data class Loaded(val stepCount: Int) : StepsScreenUiState()
    }
}
