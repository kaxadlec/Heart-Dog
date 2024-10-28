package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import com.google.android.horologist.datalayer.sample.shared.HeartRateRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateProto.HeartRateRecord
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateServiceGrpcKt

class WearHeartRateDataService : BaseGrpcDataService<HeartRateServiceGrpcKt.HeartRateServiceCoroutineImplBase>() {

    private val dataStore: DataStore<HeartRateRecord> by lazy {
        registry.protoDataStore<HeartRateRecord>(lifecycleScope)
    }

    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = lifecycleScope,
        ).apply {
            registerSerializer(HeartRateRecordSerializer)
        }
    }

    override fun buildService(): HeartRateServiceGrpcKt.HeartRateServiceCoroutineImplBase {
        return HeartRateService(dataStore)
    }
}