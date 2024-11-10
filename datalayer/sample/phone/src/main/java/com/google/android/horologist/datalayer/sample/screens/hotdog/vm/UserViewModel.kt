package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userId = MutableStateFlow<Long?>(null)
    val userId: StateFlow<Long?> get() = _userId

    // 임의의 걸음 수 업데이트 (1분마다 상수 값 사용)
    fun startUpdatingStepsEveryMinute() {
        viewModelScope.launch {
            while (true) {
                val currentUserId = _userId.value
                if (currentUserId != null) {
                    userRepository.updateSteps(currentUserId, 10)  // 임의의 상수 10을 사용
                }
                delay(60000)  // 1분 지연
            }
        }
    }

    fun startUpdatingDistanceEveryMinute() {
        viewModelScope.launch {
            while (true) {
                val currentUserId = _userId.value
                if (currentUserId != null) {
                    userRepository.updateDistance(currentUserId, 100)
                }
                delay(60000)  // 1분 지연
            }
        }
    }

    fun setUserId(id: Long) {
        Log.d("UserViewModel", "setUserId 호출: $id")
        _userId.value = id
    }

    private val _userFullInfo = MutableStateFlow<UserRepository.UserFullInfoResponse?>(null)
    val userFullInfo: StateFlow<UserRepository.UserFullInfoResponse?> get() = _userFullInfo

    fun fetchUserFullInfo(userId: Long) {
        viewModelScope.launch {
            try {
                val userInfo = userRepository.getUserFullInfo(userId)
                _userFullInfo.value = userInfo
                Log.d("UserViewModel", "Fetched user full info: $userInfo")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user full info: ${e.message}")
            }
        }
    }
}
