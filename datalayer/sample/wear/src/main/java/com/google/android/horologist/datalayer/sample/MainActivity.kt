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
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.wearable.DataMapItem
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.gms.wearable.Wearable
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.DogDataListener
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.HeartDataListener
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dogDataListener: DogDataListener // 강아지 데이터 수신 리스너 생성
    @Inject
    lateinit var heartDataListener: HeartDataListener // Heart 데이터 수신 리스너 생성

    private val petViewModel: PetViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp(petViewModel = petViewModel, userViewModel = userViewModel) // 최상위 컴포저블 실행
        }

//        petViewModel = ViewModelProvider(this).get(PetViewModel::class.java)

        // DogDataListener의 데이터 변경 콜백 설정
        dogDataListener.setOnDataReceivedListener { newState ->
            Log.d("MainActivity", "Received new pet state from DogDataListener: $newState")
            petViewModel.updatePetState(newState)
        }

        dogDataListener.setOnHasDogDataReceivedListener { newState ->
            Log.d("MainActivity", "Received new pet state from HasDogDataListener: $newState")
            userViewModel.updateHasPet(newState)
        }
        // HeartDataListener를 통해 Heart 데이터를 수신
        heartDataListener.setOnHeartDataReceivedListener { heartValue ->
            Log.d("MainActivity", "Heart data received: $heartValue")
            userViewModel.updateHeart(heartValue) // Heart 데이터 업데이트
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
                    val hasDog = dataMap.getBoolean("hasDog")
                    Log.d("DataClientCheck", "Received dogData JSON: $dogDataJson")
                    Log.d("DataClientCheck", "Received dogData Boolean hasDog: $hasDog")
                }
            }
        }
        Wearable.getDataClient(this).addListener(heartDataListener) // Listener 등록
    }

    override fun onPause() {
        Wearable.getDataClient(this).removeListener(dogDataListener) // 리스너 해제
        Wearable.getDataClient(this).removeListener(heartDataListener) // Listener 해제
        super.onPause()
    }
}
