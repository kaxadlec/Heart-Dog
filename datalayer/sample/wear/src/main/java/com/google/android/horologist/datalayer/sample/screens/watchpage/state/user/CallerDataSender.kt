package com.google.android.horologist.datalayer.sample.screens.watchpage.state.user

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable

object CallerDataSender {

    fun sendCallRequestToPhone(context: Context) {
        val putDataMapRequest = PutDataMapRequest.create("/call_request") // 데이터 경로
        val dataMap = putDataMapRequest.dataMap

        // 필요한 데이터를 설정
        dataMap.putString("action", "CALL")
        dataMap.putLong("timestamp", System.currentTimeMillis())

        // 요청 생성
        val putDataReq = putDataMapRequest.asPutDataRequest()

        // 데이터 전송
        Wearable.getDataClient(context).putDataItem(putDataReq)
            .addOnSuccessListener { Log.d("CallerDataSender", "Call request sent successfully") }
            .addOnFailureListener { e -> Log.e("CallerDataSender", "Failed to send call request", e) }
    }
}