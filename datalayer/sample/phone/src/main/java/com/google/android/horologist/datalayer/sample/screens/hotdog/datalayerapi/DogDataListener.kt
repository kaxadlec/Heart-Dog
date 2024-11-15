package com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.*
import com.google.android.gms.tasks.Tasks
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DogDataListener @Inject constructor(
    private val context: Context,
    private val dogRepository: DogRepository
) : DataClient.OnDataChangedListener {

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == "/feed_request") {
                handleFeedRequest(event)
            }
        }
    }

    private fun handleFeedRequest(event: DataEvent) {
        try {
            val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
            val heartAmount = dataMap.getInt("heartAmount", 0)

            Log.d("DogDataListener", "Received feed request with heartAmount: $heartAmount")

            // 로컬 userId 가져오기 (예: UserSessionManager)
            val userId = getCurrentUserId()

            // 하트 주기 처리
            if (userId != null) {
                val result = runBlocking { processFeedRequest(userId, heartAmount) }

                // 결과를 워치로 다시 전송
                if (result != null) {
                    sendFeedResponseToWatch(result)
                }
            } else {
                Log.e("DogDataListener", "Failed to process feed request: userId is null")
            }
        } catch (e: Exception) {
            Log.e("DogDataListener", "Error handling feed request", e)
        }
    }

    private suspend fun processFeedRequest(userId: Long, heartAmount: Int): DogRepository.GiveHeartResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val dogId = dogRepository.getDogIdByUserId(userId)
                    ?: throw IllegalStateException("Dog ID not found for userId: $userId")

                dogRepository.giveHeartToDog(userId, dogId, heartAmount)
            } catch (e: Exception) {
                Log.e("DogDataListener", "Error processing feed request", e)
                null
            }
        }
    }

    private fun sendFeedResponseToWatch(response: DogRepository.GiveHeartResponse) {
        val putDataMapRequest = PutDataMapRequest.create("/feed_response")
        val dataMap = putDataMapRequest.dataMap.apply {
            putInt("updatedHeart", response.updatedHeart ?: 0)
            putInt("updatedSatiety", response.updatedSatiety ?: 0)
        }

        val putDataReq = putDataMapRequest.asPutDataRequest()

        Wearable.getDataClient(context).putDataItem(putDataReq)
            .addOnSuccessListener { Log.d("DogDataListener", "Feed response sent successfully") }
            .addOnFailureListener { Log.e("DogDataListener", "Failed to send feed response", it) }
    }

    private fun getCurrentUserId(): Long? {
        // UserSessionManager를 활용해 userId를 가져옵니다.
        // userSessionManager.getCurrentUserId() 또는 적절한 메서드를 호출
        return 19L // 예시: 고정된 userId를 반환
    }
}