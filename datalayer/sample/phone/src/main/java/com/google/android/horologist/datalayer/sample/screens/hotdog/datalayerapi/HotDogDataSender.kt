package com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import com.google.gson.Gson

fun sendDogDataToWatch(context: Context, dog: Dog) {
    val dataClient: DataClient = Wearable.getDataClient(context)
    val dataMapRequest = PutDataMapRequest.create("/dog_data").apply {
        dataMap.putString("dogData", Gson().toJson(dog))  // JSON 문자열로 변환하여 전송
        dataMap.putLong("timestamp", System.currentTimeMillis()) // 변경된 데이터 감지를 위한 타임스탬프 추가
    }
    dataClient.putDataItem(dataMapRequest.asPutDataRequest())
        .addOnSuccessListener {
            Log.d("sendDogDataToWatch", "Data sent successfully")
        }
        .addOnFailureListener { e ->
            Log.e("sendDogDataToWatch", "Failed to send data", e)
        }
}