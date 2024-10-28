package com.google.android.horologist.datalayer.sample.grpc

import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateProto
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.copy
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import com.google.protobuf.Empty
import kotlinx.coroutines.flow.first

class HeartRateService(
    private val dataStore: DataStore<HeartRateProto.HeartRateRecord>,
) : HeartRateServiceGrpcKt.HeartRateServiceCoroutineImplBase() {

    override suspend fun get(request: Empty): HeartRateProto.HeartRateRecord {
        return dataStore.data.first()
    }

    override suspend fun put(request: HeartRateProto.HeartRateDelta): HeartRateProto.HeartRateRecord {
        return dataStore.updateData {
            it.copy {
                this.heartRate = request.heartRate
                this.update = System.currentTimeMillis().toProtoTimestamp()
            }
        }
    }
}
