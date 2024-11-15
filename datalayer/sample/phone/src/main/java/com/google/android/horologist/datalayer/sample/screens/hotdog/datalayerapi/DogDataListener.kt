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
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager


class DogDataListener @Inject constructor(
    private val context: Context,
    private val dogRepository: DogRepository,
    private val userSessionManager: UserSessionManager
) : DataClient.OnDataChangedListener {

    // onDataChanged 메서드는 데이터 이벤트를 처리하는 메서드
    override fun onDataChanged(dataEvents: DataEventBuffer) {
        Log.d("DogDataListener", "onDataChanged called with ${dataEvents.count} events")
        for (event in dataEvents) {
            val path = event.dataItem.uri.path
            Log.d("DogDataListener", "Received event with path: $path")
            if (event.type == DataEvent.TYPE_CHANGED && path == "/feed_request") {
                handleFeedRequest(event)
            } else {
                Log.d("DogDataListener", "Event not handled. Path: $path")
            }
        }
    }

    //  feed_request 이벤트를 처리하는 메서드
    private fun handleFeedRequest(event: DataEvent) {
        try {
            val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
            val heartAmount = dataMap.getInt("heartAmount", 0)
//            Log.d("DogDataListener", "Received feed request with heartAmount: $heartAmount")

            // 로컬 userId 가져오기
            val userId =  userSessionManager.getCurrentUserId()

            // 하트 주기 처리
            if (userId != null) {
//                Log.d("DogDataListener", "UserId retrieved: $userId")
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
                val dogId = dogRepository.getDogIdByUserId(userId) // userId로 dogId 가져오기
                if (dogId == null) { // dogId가 없을 경우
                    Log.e("DogDataListener", "Dog ID not found for userId: $userId")
                    return@withContext null
                }
                Log.d("DogDataListener", "Dog ID retrieved: $dogId for userId: $userId")

                val result = dogRepository.giveHeartToDog(userId, dogId, heartAmount)
                if (result != null && result.success) {
                    Log.d("DogDataListener", "giveHeartToDog succeeded. Result: $result")
                } else {
                    Log.e("DogDataListener", "giveHeartToDog failed. Result: $result")
                }
                result
            } catch (e: Exception) {
                Log.e("DogDataListener", "Error in processFeedRequest", e)
                null
            }
        }
    }

    private fun sendFeedResponseToWatch(response: DogRepository.GiveHeartResponse) {
        val putDataMapRequest = PutDataMapRequest.create("/feed_response")
        val dataMap = putDataMapRequest.dataMap.apply {
            putInt("updatedHeart", response.updatedHeart ?: 0)
            putInt("updatedSatiety", response.updatedSatiety ?: 0)
            putLong("timestamp", System.currentTimeMillis())
        }

        val putDataReq = putDataMapRequest.asPutDataRequest()

        Wearable.getDataClient(context).putDataItem(putDataReq)
            .addOnSuccessListener { Log.d("DogDataListener", "Feed response sent successfully") }
            .addOnFailureListener { Log.e("DogDataListener", "Failed to send feed response", it) }
    }


}