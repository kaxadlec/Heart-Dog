package com.google.android.horologist.datalayer.sample.di

import android.content.Context
import android.hardware.SensorManager
import com.google.android.horologist.data.ProtoDataStoreHelper.registerProtoDataListener
import com.google.android.horologist.data.TargetNodeId
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.data.proto.SampleProto
import com.google.android.horologist.data.store.ProtoDataListener
import com.google.android.horologist.datalayer.grpc.GrpcExtensions.grpcClient
import com.google.android.horologist.datalayer.sample.screens.nodes.SampleDataSerializer
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.shared.CounterValueSerializer
import com.google.android.horologist.datalayer.sample.shared.DogRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.EmojiValueSerializer
import com.google.android.horologist.datalayer.sample.shared.HeartRateRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.StepCountRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto.EmojiValue
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountServiceGrpcKt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideContext(app: Application): Context = app


//    @Singleton
//    @Provides
//    fun provideSingletonCoroutineScope(): CoroutineScope {
//        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
//    }

//    @Singleton
//    @Provides
//    fun provideSingletonCoroutineScope(): CoroutineScope {
//        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
//    }

    @Singleton
    @Provides
    fun wearDataLayerRegistry(
        @ApplicationContext applicationContext: Context,
//        singletonCoroutineScope: CoroutineScope,
    ): WearDataLayerRegistry = WearDataLayerRegistry.fromContext(
        application = applicationContext,
        coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
    ).apply {
        registerSerializer(CounterValueSerializer)
        registerSerializer(HeartRateRecordSerializer)
        registerSerializer(StepCountRecordSerializer)
        registerSerializer(SampleDataSerializer)
        registerSerializer(DogRecordSerializer)
        registerSerializer(EmojiValueSerializer)

        registerProtoDataListener(object : ProtoDataListener<SampleProto.Data> {
            override fun dataAdded(nodeId: String, path: String, value: SampleProto.Data) {
                println("Data Added: $nodeId $path $value")
            }

            override fun dataDeleted(nodeId: String, path: String) {
                println("Data Deleted: $nodeId $path")
            }
        })
    }

    @Singleton
    @Provides
    fun provideStepCountServiceStub(
        wearDataLayerRegistry: WearDataLayerRegistry,
//        singletonCoroutineScope: CoroutineScope
    ): StepCountServiceGrpcKt.StepCountServiceCoroutineStub {
        return wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        ) {
            StepCountServiceGrpcKt.StepCountServiceCoroutineStub(it)
        }
    }

    @Singleton
    @Provides
    fun provideSensorManager(
        @ApplicationContext context: Context
    ): SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    // Emoji Service 관련 Flow 및 Service 추가
    @Singleton
    @Provides
    fun emojiFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<EmojiValue> {
        return wearDataLayerRegistry.protoFlow(
            TargetNodeId.PairedPhone,
            EmojiValueSerializer,
            "emoji_data"
        )
    }

    @Singleton
    @Provides
    fun provideEmojiService(
        wearDataLayerRegistry: WearDataLayerRegistry
    ): EmojiServiceGrpcKt.EmojiServiceCoroutineStub {
        return wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        ) {
            EmojiServiceGrpcKt.EmojiServiceCoroutineStub(it)
        }
    }

}