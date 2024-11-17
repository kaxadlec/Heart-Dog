package com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.DataMap
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable

object HeartDataSender {

    fun sendHeartToWatch(context: Context, heart: Int) {
        val path = "/heart_data"

        // DataMap 생성 및 데이터 추가
        val putDataMapRequest = PutDataMapRequest.create(path).apply {
            dataMap.putInt("heart", heart)
            dataMap.putLong("timestamp", System.currentTimeMillis()) // 타임스탬프 추가
        }

        val putDataRequest = putDataMapRequest.asPutDataRequest()

        // DataClient를 사용하여 데이터 전송
        Wearable.getDataClient(context).putDataItem(putDataRequest)
            .addOnSuccessListener {
                Log.d("HeartDataSender", "Heart data sent successfully: $heart")
            }
            .addOnFailureListener { e ->
                Log.e("HeartDataSender", "Failed to send heart data", e)
            }
    }
}