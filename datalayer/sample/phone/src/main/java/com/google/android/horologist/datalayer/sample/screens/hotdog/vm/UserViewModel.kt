package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userId = MutableStateFlow<Long?>(null)
    val userId: StateFlow<Long?> get() = _userId

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
