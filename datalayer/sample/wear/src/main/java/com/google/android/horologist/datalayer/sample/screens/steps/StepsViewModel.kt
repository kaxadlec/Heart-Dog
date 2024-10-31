package com.google.android.horologist.datalayer.sample.screens.steps

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.horologist.datalayer.sample.screens.steps.data.StepsRepository
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountProto
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountServiceGrpcKt.StepCountServiceCoroutineStub
import com.google.protobuf.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepsViewModel @Inject constructor(
    val stepCountService: StepCountServiceCoroutineStub,
    val stepsRepository: StepsRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val uiState: MutableStateFlow<StepsScreenState> = MutableStateFlow(StepsScreenState())

    init {
        startStepCountWorker()

// stepsFlow() 값을 바로 수집하여 UI에 반영
        viewModelScope.launch {
            stepsRepository.stepsFlow().collect { currentStepCount ->
                uiState.update { it.copy(stepCountValue = StepCountProto.StepCountValue.newBuilder().setValue(currentStepCount).build()) }
//                // 실시간으로 updateSteps 호출하여 서버로도 전송
//                updateSteps(currentStepCount)
            }
        }
    }

    private fun startStepCountWorker() {
        val workRequest = OneTimeWorkRequestBuilder<StepCountWorker>().build()
        WorkManager.getInstance(context).enqueueUniqueWork(
            "StepCountWorker",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        println("StepCountWorker started")
    }

//    fun updateSteps(stepDifference: Int) {
//        viewModelScope.launch {
//            try {
//                val newValue: StepCountProto.StepCountValue =
//                    stepCountService.increment(stepCountDelta { delta = stepDifference })
//                uiState.update {
//                    updateIfNewer(it, newValue)
//                }
//            } catch (e: Exception) {
//                uiState.update {
//                    it.copy(error = e.message)
//                }
//            }
//        }
//    }
//
//    private fun updateIfNewer(
//        it: StepsScreenState,
//        newValue: StepCountProto.StepCountValue,
//    ): StepsScreenState {
//        val currentUpdated = it.stepCountValue?.updatedOrNull
//        return if (currentUpdated == null || currentUpdated.isBefore(newValue.updated)) {
//            it.copy(stepCountValue = newValue)
//        } else {
//            it
//        }
//    }
}

// Extension function for comparing timestamps
private fun Timestamp.isBefore(other: Timestamp): Boolean =
    seconds < other.seconds || (seconds == other.seconds && nanos < other.nanos)

// Data class for screen state
data class StepsScreenState(
    val stepCountValue: StepCountProto.StepCountValue? = null,
    val error: String? = null,
)