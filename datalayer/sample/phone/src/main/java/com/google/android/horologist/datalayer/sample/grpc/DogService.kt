package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.shared.grpc.DogProto
import com.google.android.horologist.datalayer.sample.shared.grpc.DogServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.copy
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import com.google.protobuf.Timestamp
import com.google.protobuf.Empty

class DogService @Inject constructor(
    private val dataStore: DataStore<DogProto.DogRecord>
) : DogServiceGrpcKt.DogServiceCoroutineImplBase() {

    override suspend fun get(request: Empty): DogProto.DogRecord {
        val record = dataStore.data.first()

        // 초기 데이터인 경우 (모든 값이 기본값인 경우) 기본 데이터 설정
        return if (record == DogProto.DogRecord.getDefaultInstance()) {
            val initialRecord = DogProto.DogRecord.newBuilder()
                .setDogId(1L)
                .setName("주어온 강아지")
                .setLevel(1)
                .setCurrentExp(0)
                .setMaxExp(100)
                .setSatiety(50)
                .setPosition("home")
                .setUpdate(System.currentTimeMillis().toProtoTimestamp())
                .build()

            // 초기 데이터를 DataStore에 저장
            dataStore.updateData { initialRecord }
        } else {
            record
        }

    }

    override suspend fun put(request: DogProto.DogDelta): DogProto.DogRecord {
        return dataStore.updateData {
            val builder = if (it == DogProto.DogRecord.getDefaultInstance()) {
                DogProto.DogRecord.newBuilder()
            } else {
                it.toBuilder()
            }

            builder
                .setDogId(request.dogId)
                .setName(request.name)
                .setLevel(request.level)
                .setCurrentExp(request.currentExp)
                .setMaxExp(request.maxExp)
                .setSatiety(request.satiety)
                .setPosition(request.position)
                .setUpdate(System.currentTimeMillis().toProtoTimestamp())
                .build()
        }
    }

    private fun Long.toProtoTimestamp(): Timestamp {
        return Timestamp.newBuilder()
            .setSeconds(this / 1000)
            .setNanos(((this % 1000) * 1000000).toInt())
            .build()
    }
}

