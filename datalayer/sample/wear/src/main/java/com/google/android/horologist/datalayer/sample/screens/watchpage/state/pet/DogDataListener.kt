package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.google.gson.Gson
import javax.inject.Inject

class DogDataListener @Inject constructor(): DataClient.OnDataChangedListener {

    private val _dogData = MutableStateFlow<PetUiState?>(null)
    val dogData = _dogData.asStateFlow() // 외부에서 수신 가능하도록 설정

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        Log.d("DogDataListener", "onDataChanged called")
        dataEvents.forEach { event ->
            Log.d("DogDataListener", "Data event received: ${event.dataItem.uri.path}")
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == "/dog_data") {
                val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                val dogDataJson = dataMap.getString("dogData")
                val dog = Gson().fromJson(dogDataJson, PetUiState::class.java) // PetUiState로 매핑
                Log.d("DogDataListener", "Received Dog Data: $dog")

                // 수신한 데이터 Flow로 전달
                _dogData.value = dog
            }
        }
    }
}