/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.datalayer.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.wearable.DataMapItem
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.gms.wearable.Wearable
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.DogDataListener
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dogDataListener: DogDataListener // 데이터 수신 리스너 생성

    private val petViewModel: PetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp(petViewModel = petViewModel) // 최상위 컴포저블 실행
        }

//        petViewModel = ViewModelProvider(this).get(PetViewModel::class.java)

        // DogDataListener의 데이터 변경 콜백 설정
        dogDataListener.setOnDataReceivedListener { newState ->
            Log.d("MainActivity", "Received new pet state from DogDataListener: $newState")
            petViewModel.updatePetState(newState)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "Registering DogDataListener")
        Wearable.getDataClient(this).addListener(dogDataListener) // 리스너 등록

        // 전송된 데이터 확인
        Wearable.getDataClient(this).getDataItems().addOnSuccessListener { dataItems ->
            dataItems.forEach { item ->
                Log.d("DataClientCheck", "DataItem found at path: ${item.uri.path}")
                if (item.uri.path == "/dog_data") {
                    val dataMap = DataMapItem.fromDataItem(item).dataMap
                    val dogDataJson = dataMap.getString("dogData")
                    Log.d("DataClientCheck", "Received dogData JSON: $dogDataJson")
                }
            }
        }
    }

    override fun onPause() {
        Wearable.getDataClient(this).removeListener(dogDataListener) // 리스너 해제
        super.onPause()
    }
}

//class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var tileSync: TileSync
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        lifecycleScope.launch {
//            tileSync.trackInstalledTiles(this@MainActivity)
//
//        }
//
////        val heartRateServicesRepository = (application as SampleApplication).heartRateServicesRepository
//
//        setContent {
//            WearApp()
//        }
//    }
//}
