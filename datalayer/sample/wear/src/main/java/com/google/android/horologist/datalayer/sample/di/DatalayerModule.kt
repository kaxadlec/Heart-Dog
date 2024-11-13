/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.datalayer.sample.di

import android.content.Context
import com.google.android.horologist.data.ProtoDataStoreHelper.protoFlow
import com.google.android.horologist.data.TargetNodeId
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.GrpcExtensions.grpcClient
import com.google.android.horologist.datalayer.sample.TileSync
import com.google.android.horologist.datalayer.sample.screens.heartrate.data.HeartRateServicesRepository
import com.google.android.horologist.datalayer.sample.shared.EmojiValueSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.DogProto
import com.google.android.horologist.datalayer.sample.shared.grpc.DogServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto.EmojiValue
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateProto.HeartRateRecord
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateServiceGrpcKt
import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatalayerModule {

//    @ActivityRetainedScoped
//    @Provides
//    fun coroutineScope(
//        activityRetainedLifecycle: ActivityRetainedLifecycle,
//    ): CoroutineScope {
//        return CoroutineScope(SupervisorJob() + Dispatchers.Default).also {
//            activityRetainedLifecycle.addOnClearedListener {
//                it.cancel()
//            }
//        }
//    }
    @ActivityRetainedScoped
    @Provides
    fun wearDataLayerAppHelper(
        @ApplicationContext applicationContext: Context,
        wearDataLayerRegistry: WearDataLayerRegistry,
//        activityCoroutineScope: CoroutineScope,
    ) = WearDataLayerAppHelper(
        context = applicationContext,
        registry = wearDataLayerRegistry,
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
    )

    @ActivityRetainedScoped
    @Provides
    fun counterFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<GrpcDemoProto.CounterValue> =
        wearDataLayerRegistry.protoFlow(TargetNodeId.PairedPhone)

    @ActivityRetainedScoped
    @Provides
    fun counterService(
        wearDataLayerRegistry: WearDataLayerRegistry,
//        activityCoroutineScope: CoroutineScope,
    ): CounterServiceGrpcKt.CounterServiceCoroutineStub =
        wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        ) {
            CounterServiceGrpcKt.CounterServiceCoroutineStub(it)
        }

    // Emoji Service 관련 Flow 및 Service 추가
    @ActivityRetainedScoped
    @Provides
    fun emojiFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<EmojiValue> {
        return wearDataLayerRegistry.protoFlow(
            TargetNodeId.PairedPhone,
            EmojiValueSerializer,
            "emoji_data"
        )
    }

    @ActivityRetainedScoped
    @Provides
    fun emojiService(
        wearDataLayerRegistry: WearDataLayerRegistry
    ): EmojiServiceGrpcKt.EmojiServiceCoroutineStub {
        return wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        ) {
            EmojiServiceGrpcKt.EmojiServiceCoroutineStub(it)
        }
    }


    /* HeartRateService */
    @ActivityRetainedScoped
    @Provides
    fun heartRateFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<HeartRateRecord> =
        wearDataLayerRegistry.protoFlow(TargetNodeId.PairedPhone)

//    fun heartRateFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<HeartRateRecord> =
//        wearDataLayerRegistry.protoFlow(TargetNodeId.PairedPhone)


    @ActivityRetainedScoped
    @Provides
    fun heartRateService(
        wearDataLayerRegistry: WearDataLayerRegistry,
//        activityCoroutineScope: CoroutineScope,
    ): HeartRateServiceGrpcKt.HeartRateServiceCoroutineStub =
        wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        ) {
            HeartRateServiceGrpcKt.HeartRateServiceCoroutineStub(it)
        }

    @ActivityRetainedScoped
    @Provides
    fun provideHeartRateServicesRepository(@ApplicationContext context: Context): HeartRateServicesRepository {
        return HeartRateServicesRepository(context)
    }

    @ActivityRetainedScoped
    @Provides
    fun tileSync(
        wearDataLayerRegistry: WearDataLayerRegistry,
        wearDataLayerAppHelper: WearDataLayerAppHelper,
    ): TileSync = TileSync(wearDataLayerRegistry, wearDataLayerAppHelper)


    /* DogService */
    @ActivityRetainedScoped
    @Provides
    fun dogFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<DogProto.DogRecord> =
        wearDataLayerRegistry.protoFlow(TargetNodeId.PairedPhone)

    @ActivityRetainedScoped
    @Provides
    fun dogService(
        wearDataLayerRegistry: WearDataLayerRegistry,
    ): DogServiceGrpcKt.DogServiceCoroutineStub =
        wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        ) {
            DogServiceGrpcKt.DogServiceCoroutineStub(it)
        }

}
