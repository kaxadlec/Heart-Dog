package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogRepository: DogRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    init {
        Log.d("DogViewModel", "새로운 DogViewModel 인스턴스 생성됨: ${this.hashCode()}")
    }

    private val _dogId = MutableStateFlow<Long?>(null)
    private val _dogDetails = MutableStateFlow<Dog?>(null)
    val dogDetails: StateFlow<Dog?> = _dogDetails.asStateFlow()

    private val _giveHeartResult = MutableStateFlow<DogRepository.GiveHeartResponse?>(null)
    val giveHeartResult: StateFlow<DogRepository.GiveHeartResponse?> = _giveHeartResult.asStateFlow()

    fun initUserAndSaveDogSession(userId: Long, userViewModel: UserViewModel) {

        runBlocking {
//        viewModelScope.launch {
            try {
                Log.d("DogViewModel", "initUserAndSaveDogSession 시작: userId = $userId")
                val dogId = dogRepository.getDogIdByUserId(userId)
                Log.d("DogViewModel", "getDogIdByUserId 결과: dogId = $dogId")

                _dogId.value = dogId

                if (dogId != null) {
                    val details = dogRepository.getDogDetailsById(dogId)
                    Log.d("DogViewModel", "getDogDetailsById 결과: details = $details")
                    // emit 대신 value 사용
                    _dogDetails.value = details

                    userSessionManager.saveDogId(dogId)
                    Log.d("DogViewModel", "saveDogId 완료")
                } else {
                    Log.e("DogViewModel", "dogId is null")
                    _dogDetails.value = null
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Error in initUserAndSaveDogSession", e)
                _dogDetails.value = null
            }
        }
    }

    fun fetchDogIdAndDetails(userId: Long) {
        viewModelScope.launch {
            try {
                val dogId = dogRepository.getDogIdByUserId(userId)
                Log.d("DogViewModel", "Fetched dogId: $dogId for userId: $userId")
                _dogId.value = dogId

                if (dogId != null) {
                    val details = dogRepository.getDogDetailsById(dogId)
                    Log.d("DogViewModel", "Fetched dogDetails: $details")
                    // emit을 사용하여 명시적으로 상태 업데이트
                    _dogDetails.emit(details)
                } else {
                    Log.e("DogViewModel", "No dog found for userId: $userId")
                    _dogDetails.emit(null)
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Error fetching dog details: ${e.message}", e)
                _dogDetails.emit(null)
            }
        }
    }

    // 강아지에게 하트 주기
    fun giveHeartToDog(userId: Long, heartAmount: Int = 5) {
        viewModelScope.launch {
            try {
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

    fun updateDogPosition(userId: Long) {
        viewModelScope.launch {
            try {
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

    fun saveDogSession(userId: Long) {
        viewModelScope.launch {
            val dogId = dogRepository.getDogIdByUserId(userId)
            _dogId.value = dogId
            if (dogId != null) {
                userSessionManager.saveDogId(dogId)
            } else {
                Log.e("DogViewModel", "saveDogSeession : DogId is null")
            }
        }

    }

}