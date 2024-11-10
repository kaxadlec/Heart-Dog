package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {
    private val _userId = MutableStateFlow<Long?>(null)
    val userId: StateFlow<Long?> get() = _userId

    init {
        Log.d("UserViewModel", "UserViewModel init 시작")
        // userId 초기값을 어디서 설정하는지 확인 필요
    }

    fun setUserId(id: Long) {
        Log.d("UserViewModel", "setUserId 호출: $id")
        _userId.value = id
    }
}
