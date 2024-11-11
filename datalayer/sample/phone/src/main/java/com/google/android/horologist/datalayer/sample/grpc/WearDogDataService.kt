package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import com.google.android.horologist.datalayer.sample.shared.DogRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.DogProto.DogRecord
import com.google.android.horologist.datalayer.sample.shared.grpc.DogServiceGrpcKt

class WearDogDataService : BaseGrpcDataService<DogServiceGrpcKt.DogServiceCoroutineImplBase>() {

    private val dataStore: DataStore<DogRecord> by lazy {
        registry.protoDataStore<DogRecord>(lifecycleScope)
    }

    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = lifecycleScope,
        ).apply {
            registerSerializer(DogRecordSerializer)
        }
    }

    override fun buildService(): DogServiceGrpcKt.DogServiceCoroutineImplBase {
        return DogService(dataStore)
    }
}