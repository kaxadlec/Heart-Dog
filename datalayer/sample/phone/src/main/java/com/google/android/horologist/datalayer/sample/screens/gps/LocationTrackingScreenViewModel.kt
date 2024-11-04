package com.google.android.horologist.datalayer.sample.screens.gps

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.RequiresPermission
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingProto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@HiltViewModel
class LocationTrackingScreenViewModel
    @Inject constructor(
        private val phoneDataLayerAppHelper: PhoneDataLayerAppHelper,
        private val registry: WearDataLayerRegistry,
    ) : ViewModel() {

    private var initializeCalled = false
    private val apiAvailable = MutableStateFlow(false)

    private lateinit var locationTrackingDataStore: DataStore<LocationTrackingProto.LocationTrackingRecord>

    private val _uiState = MutableStateFlow<LocationTrackingScreenUiState>(LocationTrackingScreenUiState.Idle)
    val uiState: StateFlow<LocationTrackingScreenUiState> = _uiState

    init {
        initialize()
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
    private val locationTrackingState: Flow<LocationTrackingProto.LocationTrackingRecord?> =
        apiAvailable.flatMapLatest { apiAvailable ->
            if (!apiAvailable) {
                flowOf(null)
            } else {
                locationTrackingDataStore.data
            }
        }

    @SuppressLint("MissingPermission")
    @MainThread
    fun initialize() {
        if (initializeCalled) return
        initializeCalled = true

        viewModelScope.launch {
            _uiState.value = LocationTrackingScreenUiState.CheckingApiAvailability
            if (!phoneDataLayerAppHelper.isAvailable()) {
                _uiState.value = LocationTrackingScreenUiState.ApiNotAvailable
            } else {
                apiAvailable.value = true
                locationTrackingDataStore =
                    registry.protoDataStore<LocationTrackingProto.LocationTrackingRecord>(viewModelScope)
                _uiState.value = LocationTrackingScreenUiState.Loading
            }

            // LocationTrackingRepository에서 locationTrackingRecord를 가져와 상태 업데이트
            locationTrackingState.collect { locationTracking ->
                Log.i("location start", "${locationTracking?.latitude}, ${locationTracking?.longitude}")

                if (locationTracking != null) {
                    when (_uiState.value) {
                        LocationTrackingScreenUiState.Loading,
                        is LocationTrackingScreenUiState.Loaded,
                            -> {
                            _uiState.value = LocationTrackingScreenUiState.Loaded(
                                locationTracking = LocationTracking(
                                    locationTracking.latitude,
                                    locationTracking.longitude
                                )
                            )
                        }
                        else -> { /* noop */ }
                    }
                }

                Log.i("location end", "${locationTracking?.latitude}, ${locationTracking?.longitude}")
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun updateCurrentLocation() {
        viewModelScope.launch {
//            saveCurrentLocation()
        }
    }
}


public sealed class LocationTrackingScreenUiState {
    public data object Idle : LocationTrackingScreenUiState()
    public data object Loading : LocationTrackingScreenUiState()
    public data class Loaded(var locationTracking: LocationTracking) : LocationTrackingScreenUiState()
    public data object CheckingApiAvailability : LocationTrackingScreenUiState()
    public data object ApiNotAvailable : LocationTrackingScreenUiState()
}
