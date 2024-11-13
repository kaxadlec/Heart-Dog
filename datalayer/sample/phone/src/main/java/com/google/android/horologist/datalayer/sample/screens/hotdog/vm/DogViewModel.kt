package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DogViewModel(
    private val userViewModel: UserViewModel,
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _dogId = MutableStateFlow<Long?>(null)
    private val _dogDetails = MutableStateFlow<Dog?>(null)
    val dogDetails: StateFlow<Dog?> = _dogDetails.asStateFlow()

    private val _giveHeartResult = MutableStateFlow<DogRepository.GiveHeartResponse?>(null)
    val giveHeartResult: StateFlow<DogRepository.GiveHeartResponse?> = _giveHeartResult.asStateFlow()

    init {
        // userId가 변경될 때마다 강아지 정보를 가져옴
        userViewModel.userId
            .filterNotNull()
            .onEach { userId ->
                fetchDogIdAndDetails(userId)
            }
            .launchIn(viewModelScope)
    }

    fun fetchDogIdAndDetails(userId: Long) {
        viewModelScope.launch {
            try {
                val dogId = dogRepository.getDogIdByUserId(userId)
                _dogId.value = dogId

                if (dogId != null) {
                    _dogDetails.value = dogRepository.getDogDetailsById(dogId)
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Error fetching dog details: ${e.message}")
            }
        }
    }

    // 강아지에게 하트 주기
    fun giveHeartToDog(heartAmount: Int = 5) {
        viewModelScope.launch {
            try {
                val userId = userViewModel.userId.value ?: return@launch
                val dogId = _dogId.value ?: return@launch

                val result = dogRepository.giveHeartToDog(userId, dogId, heartAmount)
                _giveHeartResult.value = result

                // 성공적으로 하트를 준 경우 강아지 정보 새로고침
                if (result?.success == true) {
                    fetchDogIdAndDetails(userId)
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Error giving heart to dog: ${e.message}")
            }
        }
    }

    fun updateDogPosition() {
        viewModelScope.launch {
            try {
                val userId = userViewModel.userId.value ?: return@launch
                val dogId = _dogId.value ?: return@launch

                val success = dogRepository.updateDogPosition(dogId, userId)
                if (!success) {
                    Log.e("DogViewModel", "강아지 위치 업데이트 실패")
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Error updating dog position: ${e.message}")
            }
        }
    }
}