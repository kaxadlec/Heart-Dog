package com.google.android.horologist.datalayer.sample.grpc

import android.util.Log
import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountProto
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountServiceGrpcKt
import com.google.android.horologist.datalayer.sample.shared.grpc.copy
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import com.google.protobuf.Empty
import kotlinx.coroutines.flow.first

class StepCountService(
    private val dataStore: DataStore<StepCountProto.StepCountValue>
) : StepCountServiceGrpcKt.StepCountServiceCoroutineImplBase() {

    // 현재 걸음 수를 반환
    override suspend fun getCurrentStepCount(request: Empty): StepCountProto.StepCountValue {
        Log.d("StepCountService", "getCurrentStepCount() called")
        val currentData = dataStore.data.first()
        Log.d("StepCountService", "Current step count from dataStore: ${currentData.value}")
        return currentData
    }

    // 걸음 수를 업데이트하는 메서드
    override suspend fun setCurrentStepCount(request: StepCountProto.StepCountValue): StepCountProto.StepCountValue {
        return dataStore.updateData { currentData ->
            currentData.copy {
                value = request.value
                updated = System.currentTimeMillis().toProtoTimestamp()
            }.also {
                Log.d("StepCountService", "Updated step count in dataStore: ${it.value}")
            }
        }
    }

}
