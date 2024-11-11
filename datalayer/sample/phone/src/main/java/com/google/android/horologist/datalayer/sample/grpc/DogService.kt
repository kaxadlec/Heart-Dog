package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.shared.grpc.DogProto
import com.google.android.horologist.datalayer.sample.shared.grpc.DogServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.copy
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import com.google.protobuf.Empty
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DogService @Inject constructor(
    private val dataStore: DataStore<DogProto.DogRecord>
) : DogServiceGrpcKt.DogServiceCoroutineImplBase() {

    override suspend fun get(request: Empty): DogProto.DogRecord {
        return dataStore.data.first()
    }

    override suspend fun put(request: DogProto.DogDelta): DogProto.DogRecord {
        return dataStore.updateData {
            it.copy {
                this.dogId = request.dogId
                this.name = request.name
                this.level = request.level
                this.currentExp = request.currentExp
                this.maxExp = request.maxExp
                this.satiety = request.satiety
                this.position = request.position
                this.update = System.currentTimeMillis().toProtoTimestamp()
            }
        }
    }
}

