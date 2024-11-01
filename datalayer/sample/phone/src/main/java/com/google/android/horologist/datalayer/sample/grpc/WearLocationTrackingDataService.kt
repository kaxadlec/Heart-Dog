package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import com.google.android.horologist.datalayer.sample.shared.LocationTrackingRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingProto.LocationTrackingRecord
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingServiceGrpcKt

class WearLocationTrackingDataService : BaseGrpcDataService<LocationTrackingServiceGrpcKt.LocationTrackingServiceCoroutineImplBase>() {
    private val dataStore: DataStore<LocationTrackingRecord> by lazy {
        registry.protoDataStore<LocationTrackingRecord>(lifecycleScope)
    }

    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = lifecycleScope,
        ).apply {
            registerSerializer(LocationTrackingRecordSerializer)
        }
    }

    override fun buildService(): LocationTrackingServiceGrpcKt.LocationTrackingServiceCoroutineImplBase {
        return LocationTrackingService(dataStore)
    }
}