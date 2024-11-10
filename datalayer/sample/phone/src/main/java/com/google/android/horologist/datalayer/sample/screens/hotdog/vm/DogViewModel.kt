package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import android.util.Log
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import kotlinx.coroutines.flow.*

class DogViewModel(
    private val userViewModel: UserViewModel,
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _dogId = MutableStateFlow<Long?>(null)
    val dogId: StateFlow<Long?> get() = _dogId

    init {
        Log.d("DogViewModel", "DogViewModel init 시작")
        Log.d("DogViewModel", "초기 userViewModel.userId: ${userViewModel.userId.value}")

        // userId가 설정되면 dogId를 가져옴
        userViewModel.userId
            .filterNotNull() // userId가 null이 아닌 경우만 처리
            .onEach { userId ->
                Log.d("DogViewModel", "userId 변경 감지: $userId")
                if (userId != null) {
                    fetchDogIdByUserId(userId)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchDogIdByUserId(userId: Long) {
        viewModelScope.launch {
            val fetchedDogId = dogRepository.getDogIdByUserId(userId)
            _dogId.value = fetchedDogId
        }
    }

    private val _dogDetails = MutableStateFlow<Dog?>(null)
    val dogDetails: StateFlow<Dog?> get() = _dogDetails

    init {
        userViewModel.userId
            .filterNotNull()
            .onEach { userId ->
                fetchDogIdAndDetails(userId)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchDogIdAndDetails(userId: Long) {
        viewModelScope.launch {
            val dogId = dogRepository.getDogIdByUserId(userId)
            if (dogId != null) {
                // getDogDetailsById가 suspend 함수로 되어 있는지 확인 필요
                _dogDetails.value = dogRepository.getDogDetailsById(dogId)
            }
        }
    }

}
