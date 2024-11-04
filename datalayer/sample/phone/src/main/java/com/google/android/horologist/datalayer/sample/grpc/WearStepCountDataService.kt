package com.google.android.horologist.datalayer.sample.grpc

import android.annotation.SuppressLint
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import com.google.android.horologist.datalayer.sample.shared.StepCountRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountProto.StepCountValue
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountServiceGrpcKt

@SuppressLint("LongLogTag")
class WearStepCountDataService : BaseGrpcDataService<StepCountServiceGrpcKt.StepCountServiceCoroutineImplBase>() {


    private val TAG = "WearStepCountDataService"

    private val dataStore: DataStore<StepCountValue> by lazy {
        registry.protoDataStore<StepCountValue>(lifecycleScope)
    }

    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = lifecycleScope,
        ).apply {
            registerSerializer(StepCountRecordSerializer)
            Log.d(TAG, "WearDataLayerRegistry initialized and StepCountRecordSerializer registered")
        }
    }

    override fun buildService(): StepCountServiceGrpcKt.StepCountServiceCoroutineImplBase {
        return StepCountService(dataStore)
    }
}
