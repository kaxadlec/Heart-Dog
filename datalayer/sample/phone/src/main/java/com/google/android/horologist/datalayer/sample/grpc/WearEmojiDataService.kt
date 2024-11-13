package com.google.android.horologist.datalayer.sample.grpc

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import com.google.android.horologist.datalayer.sample.shared.EmojiValueSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto.EmojiValue
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt

class WearEmojiDataService : BaseGrpcDataService<EmojiServiceGrpcKt.EmojiServiceCoroutineImplBase>() {

    private val dataStore: DataStore<EmojiValue> by lazy {
        registry.protoDataStore<EmojiValue>(lifecycleScope).also {
            Log.d("WearEmojiDataService", "DataStore initialized")
        }

    }

    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = lifecycleScope,
        ).apply {
            registerSerializer(EmojiValueSerializer)
            Log.d("WearEmojiDataService", "WearDataLayerRegistry initialized and EmojiValueSerializer registered")

        }
    }

    override fun buildService(): EmojiServiceGrpcKt.EmojiServiceCoroutineImplBase {
        Log.d("WearEmojiDataService", "Building EmojiService")
        return EmojiService(dataStore)
    }
}
