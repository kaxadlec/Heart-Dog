package com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.google.android.gms.wearable.WearableListenerService
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CallDataListener @Inject constructor(
    private val context: Context,
    private val userSessionManager: UserSessionManager,
    private val userRepository: UserRepository,
    private val dogRepository: DogRepository,
) : DataClient.OnDataChangedListener {

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        Log.d("CallDataListener", "onDataChanged called with ${dataEvents.count} events")
        for (event in dataEvents) {
            val path = event.dataItem.uri.path
            Log.d("CallDataListener", "Received event with path: $path")
            if (event.type == DataEvent.TYPE_CHANGED && path == "/call_request") {
                handleCallRequest(event)
            } else {
                Log.d("CallDataListener", "Event not handled. Path: $path")
            }
        }
    }

    private fun handleCallRequest(event: DataEvent) {
        try {
            val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
            val timestamp = dataMap.getLong("timestamp", 0L) // 요청 시간 확인
            Log.d("CallDataListener", "Received call request with timestamp: $timestamp")

            // 로컬 userId 가져오기
            val userId = userSessionManager.getCurrentUserId()

            if (userId != null) {
                Log.d("CallDataListener", "UserId retrieved: $userId")
                // 커플 정보 가져오기
                fetchAndUpdateDogPosition(userId)

                // 워치에 성공적으로 처리됐음을 알림
                sendCallResponseToWatch(true)
            } else {
                Log.e("CallDataListener", "Failed to process call request: userId is null")
            }
        } catch (e: Exception) {
            Log.e("CallDataListener", "Error handling call request", e)
        }
    }

    private fun fetchAndUpdateDogPosition(userId: Long) {
        runBlocking {
            try {
                val userFullInfo = withContext(Dispatchers.IO) {
                    userRepository.getUserFullInfo(userId)
                }

                if (userFullInfo?.couple_info != null) {
                    val coupleInfo = userFullInfo.couple_info
                    Log.d(
                        "CallDataListener",
                        "Couple Info Retrieved: coupleId=${coupleInfo.couple_id}, host=${coupleInfo.host}, guest=${coupleInfo.guest}"
                    )

                    // 본인의 ID와 상대방의 ID 가져오기
                    val partnerId = if (userId == coupleInfo.host) coupleInfo.guest else coupleInfo.host

                    if (partnerId != null) {
                        Log.d("CallDataListener", "Updating Dog Positions for userId=$userId and partnerId=$partnerId")

                        // 본인의 강아지 위치 업데이트
                        val userDogId = dogRepository.getDogIdByUserId(userId)
                        if (userDogId != null) {
                            val updated = dogRepository.updateDogPosition(userDogId, userId)
                            if (updated) {
                                Log.d("CallDataListener", "User's dog position updated: userId=$userId, dogId=$userDogId")
                            } else {
                                Log.e("CallDataListener", "Failed to update user's dog position: userId=$userId")
                            }
                        } else {
                            Log.e("CallDataListener", "No dogId found for userId: $userId")
                        }

//                        // 상대방 강아지 위치 업데이트
//                        val partnerDogId = dogRepository.getDogIdByUserId(partnerId)
//                        if (partnerDogId != null) {
//                            val updated = dogRepository.updateDogPosition(partnerDogId, userId)
//                            if (updated) {
//                                Log.d("CallDataListener", "Partner's dog position updated: partnerId=$partnerId, dogId=$partnerDogId")
//                            } else {
//                                Log.e("CallDataListener", "Failed to update partner's dog position: partnerId=$partnerId")
//                            }
//                        } else {
//                            Log.e("CallDataListener", "No dogId found for partnerId: $partnerId")
//                        }
                    } else {
                        Log.e("CallDataListener", "Partner ID is null, cannot update dog position.")
                    }
                } else {
                    Log.e("CallDataListener", "No couple info available for userId: $userId")
                }
            } catch (e: Exception) {
                Log.e("CallDataListener", "Error fetching or updating dog position", e)
            }
        }
    }


    private fun sendCallResponseToWatch(success: Boolean) {
        val putDataMapRequest = PutDataMapRequest.create("/call_response")
        val dataMap = putDataMapRequest.dataMap.apply {
            putBoolean("success", success)
            putLong("timestamp", System.currentTimeMillis())
        }

        val putDataReq = putDataMapRequest.asPutDataRequest()

        Wearable.getDataClient(context).putDataItem(putDataReq)
            .addOnSuccessListener { Log.d("CallDataListener", "Call response sent successfully") }
            .addOnFailureListener { Log.e("CallDataListener", "Failed to send call response", it) }
    }
}