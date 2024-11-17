package com.google.android.horologist.datalayer.sample.screens.watchpage.state.user

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.Wearable
import com.google.android.gms.wearable.WearableListenerService
import javax.inject.Inject

class HeartDataListener @Inject constructor(): DataClient.OnDataChangedListener {

    // Heart 데이터 수신 후 처리할 콜백
    private var onHeartDataReceivedListener: ((Int) -> Unit)? = null

    fun setOnHeartDataReceivedListener(listener: (Int) -> Unit) {
        onHeartDataReceivedListener = listener
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == "/heart_data") {
                val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                val heartValue = dataMap.getInt("heart", 0)

                Log.d("HeartDataListener", "Heart data received: $heartValue")

                // Heart 데이터를 전달
                onHeartDataReceivedListener?.invoke(heartValue)
            }
        }
    }
}