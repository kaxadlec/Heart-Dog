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

    private var onDataReceived: ((PetUiState) -> Unit)? = null

    fun setOnDataReceivedListener(listener: (PetUiState) -> Unit) {
        onDataReceived = listener
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        Log.d("DogDataListener", "onDataChanged called")
        dataEvents.forEach { event ->
            Log.d("DogDataListener", "Data event received: ${event.dataItem.uri.path}")
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == "/dog_data") {
                val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                val dogDataJson = dataMap.getString("dogData")
                val dog = Gson().fromJson(dogDataJson, PetUiState::class.java) // PetUiState로 매핑
                Log.d("DogDataListener", "Parsed PetUiState: $dog")

                onDataReceived?.invoke(dog) ?: Log.w("DogDataListener", "onDataReceived listener is not set") // 콜백을 통해 MainActivity에 데이터 전달
            }
        }
    }
}