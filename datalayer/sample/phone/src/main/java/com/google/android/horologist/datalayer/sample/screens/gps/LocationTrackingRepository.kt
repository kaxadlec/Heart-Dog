package com.google.android.horologist.datalayer.sample.screens.gps

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingProto
import com.google.android.horologist.datalayer.sample.shared.grpc.locationTrackingRecord
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationTrackingRepository @Inject constructor(
    registry: WearDataLayerRegistry
) {

    private val serviceScope = CoroutineScope(Dispatchers.IO)
    private val locationTrackingDataStore: DataStore<LocationTrackingProto.LocationTrackingRecord>
     = registry.protoDataStore<LocationTrackingProto.LocationTrackingRecord>(serviceScope)

    private var locationTracking = LocationTracking(0.0, 0.0)

    suspend fun findLocation(): LocationTracking {
        val locationTracking = locationTrackingDataStore.data.first()
        return LocationTracking(
            latitude = locationTracking.latitude,
            longitude = locationTracking.longitude
        )
    }

    fun saveLocation(latitude: Double, longitude: Double) {
        locationTracking = LocationTracking(latitude = latitude, longitude = longitude)

        serviceScope.launch {
            locationTrackingDataStore.updateData {
                locationTrackingRecord {
                    this.latitude = latitude
                    this.longitude = longitude
                    this.timestamp = System.currentTimeMillis().toProtoTimestamp()
                }
            }
            Log.d("location repository", "save")
        }
    }
}