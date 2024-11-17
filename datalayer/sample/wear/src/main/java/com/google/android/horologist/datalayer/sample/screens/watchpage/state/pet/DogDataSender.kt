package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.DataMap
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable

object DogDataSender {

    fun sendFeedRequestToPhone(context: Context, heartAmount: Int = 5) {
        // DataMap 생성 및 설정
        val putDataMapRequest = PutDataMapRequest.create("/feed_request")
        val dataMap = putDataMapRequest.dataMap
        dataMap.putInt("heartAmount", heartAmount)
        dataMap.putLong("timestamp", System.currentTimeMillis())
        Log.d("DogDataSender", "Sending data to path: /feed_request with heartAmount: $heartAmount")

        // 요청 생성
        val putDataReq = putDataMapRequest.asPutDataRequest()

        // 데이터 전송
        Wearable.getDataClient(context).putDataItem(putDataReq)
            .addOnSuccessListener { Log.d("DogDataSender", "Feed request sent successfully") }
            .addOnFailureListener { Log.e("DogDataSender", "Failed to send feed request", it) }
    }
}