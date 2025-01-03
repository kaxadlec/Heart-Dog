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
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import com.google.android.horologist.data.TargetNodeId
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.GrpcExtensions.grpcClient
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper
import com.google.android.horologist.datalayer.sample.grpc.EmojiService
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import com.google.android.horologist.datalayer.sample.shared.CounterValueSerializer
import com.google.android.horologist.datalayer.sample.shared.DogRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.EmojiValueSerializer
import com.google.android.horologist.datalayer.sample.shared.HeartRateRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.LocationTrackingRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.StepCountRecordSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountServiceGrpcKt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatalayerModule {

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Singleton
    @Provides
    fun phoneDataLayerAppHelper(
        @ApplicationContext applicationContext: Context,
        wearDataLayerRegistry: WearDataLayerRegistry,
    ) = PhoneDataLayerAppHelper(
        context = applicationContext,
        registry = wearDataLayerRegistry,
    )

    @Singleton
    @Provides
    fun wearDataLayerRegistry(
        @ApplicationContext applicationContext: Context,
        coroutineScope: CoroutineScope,
    ): WearDataLayerRegistry = WearDataLayerRegistry.fromContext(
        application = applicationContext,
        coroutineScope = coroutineScope,
    ).apply {
        registerSerializer(CounterValueSerializer)
        registerSerializer(HeartRateRecordSerializer)
        registerSerializer(StepCountRecordSerializer)
        registerSerializer(LocationTrackingRecordSerializer)
        registerSerializer(DogRecordSerializer)
        registerSerializer(EmojiValueSerializer)
    }


    @Singleton
    @Provides
    fun provideStepCountServiceStub(
        wearDataLayerRegistry: WearDataLayerRegistry
    ): StepCountServiceGrpcKt.StepCountServiceCoroutineStub {
        Log.d("DatalayerModule", "Initializing StepCountServiceStub with TargetNodeId: PairedPhone")

        return wearDataLayerRegistry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        ) {
            StepCountServiceGrpcKt.StepCountServiceCoroutineStub(it).also{
                Log.d("DatalayerModule", "StepCountServiceCoroutineStub created")

            }
        }

    }

    @Singleton
    @Provides
    fun provideEmojiDataStore(
        @ApplicationContext context: Context
    ): DataStore<EmojiProto.EmojiValue> {
        return androidx.datastore.core.DataStoreFactory.create(
            serializer = EmojiValueSerializer,
            produceFile = { context.dataStoreFile("emoji_prefs.pb") },
            scope = CoroutineScope(Dispatchers.IO)
        )
    }

    @Singleton
    @Provides
    fun provideEmojiService(
        emojiDataStore: DataStore<EmojiProto.EmojiValue>,
         notificationRepository: NotificationRepository,
        userSessionManager: UserSessionManager
    ): EmojiService {
        return EmojiService(
            emojiDataStore,
            notificationRepository,
            userSessionManager
        )
    }

    //    @Singleton
//    @Provides
//    fun provideEmojiFlow(
//        wearDataLayerRegistry: WearDataLayerRegistry
//    ): Flow<EmojiProto.EmojiValue> {
//        Log.d("DatalayerModule", "Providing Emoji Flow")
//        return wearDataLayerRegistry.protoFlow(
//            TargetNodeId.PairedPhone,
//            EmojiValueSerializer,
//            "emoji_data"
//        )
//    }
//
//    @Singleton
//    @Provides
//    fun provideEmojiService(
//        wearDataLayerRegistry: WearDataLayerRegistry,
//        coroutineScope: CoroutineScope
//    ): EmojiServiceGrpcKt.EmojiServiceCoroutineStub {
//        Log.d("DatalayerModule", "Initializing EmojiServiceStub")
//        return wearDataLayerRegistry.grpcClient(
//            nodeId = TargetNodeId.PairedPhone,
//            coroutineScope = coroutineScope
//        ) {
//            EmojiServiceGrpcKt.EmojiServiceCoroutineStub(it)
//        }
//    }







//    fun heartRateFlow(wearDataLayerRegistry: WearDataLayerRegistry): Flow<HeartRateRecord> =
//        wearDataLayerRegistry.protoFlow(TargetNodeId.PairedPhone)

}
