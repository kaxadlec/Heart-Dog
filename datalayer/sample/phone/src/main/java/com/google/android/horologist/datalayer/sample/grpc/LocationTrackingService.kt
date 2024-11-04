package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingProto
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.copy
import com.google.protobuf.Empty
import kotlinx.coroutines.flow.first

class LocationTrackingService(
    private val dataStore: DataStore<LocationTrackingProto.LocationTrackingRecord>,
) : LocationTrackingServiceGrpcKt.LocationTrackingServiceCoroutineImplBase() {

    override suspend fun getCurrentLocation(request: Empty): LocationTrackingProto.LocationTrackingRecord {
        return dataStore.data.first()
    }

    override suspend fun updateLocation(request: LocationTrackingProto.LocationTrackingRecord): LocationTrackingProto.LocationTrackingRecord {
        return dataStore.updateData {
            it.copy {
                this.latitude = request.latitude
                this.longitude = request.longitude
                this.timestamp = request.timestamp
            }
        }
    }
}