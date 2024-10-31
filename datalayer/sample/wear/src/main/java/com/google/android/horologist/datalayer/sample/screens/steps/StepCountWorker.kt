package com.google.android.horologist.datalayer.sample.screens.steps

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.horologist.datalayer.sample.screens.steps.data.StepsRepository
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountProto
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountServiceGrpcKt
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class StepCountWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val stepCountService : StepCountServiceGrpcKt.StepCountServiceCoroutineStub,
    private val stepsRepository: StepsRepository
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        println("StepCountWorker started")  // Worker 시작 로그

        return try {
            // `currentStepCount`를 SetCurrentStepCount로 서버에 직접 전송
            val currentStepCount = stepsRepository.stepsFlow().first()
            val response = stepCountService.setCurrentStepCount(
                StepCountProto.StepCountValue.newBuilder().setValue(currentStepCount).build()
            )
            println("Successfully sent step count to server: ${response.value}")
            Result.success()
        } catch (e: Exception) {
            println("Error sending step count to server: ${e.message}")
            Result.retry()
        }

    }

    // Factory 생성자 추가
    @AssistedFactory
    interface Factory : ChildWorkerFactory {
        override fun create(context: Context, workerParameters: WorkerParameters): StepCountWorker
    }
}
