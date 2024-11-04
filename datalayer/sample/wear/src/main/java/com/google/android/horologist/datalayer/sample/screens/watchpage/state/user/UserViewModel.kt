package com.google.android.horologist.datalayer.sample.screens.watchpage.state.user

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// UserViewModel.kt
@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun updateUser(
        userId: String? = null,
        steps: Int? = null,
        distance: Float? = null,
        heart: Int? = null,
        hasPet: Boolean? = null
    ) {
        _uiState.update { prevState ->
            prevState.copy(
                userId = userId ?: prevState.userId,
                steps = steps ?: prevState.steps,
                distance = distance ?: prevState.distance,
                heart = heart ?: prevState.heart,
                hasPet = hasPet ?: prevState.hasPet
            )
        }
    }

    // 개별 업데이트 함수들
    fun updateSteps(steps: Int) {
        _uiState.update { it.copy(steps = steps) }
    }

    fun updateDistance(distance: Float) {
        _uiState.update { it.copy(distance = distance) }
    }

    fun updateHeart(heart: Int) {
        _uiState.update { it.copy(heart = heart) }
    }

    fun updateUserId(userId: String) {
        _uiState.update { it.copy(userId = userId) }
    }

    fun updateHasPet(hasPet: Boolean) {  // 추가된 함수
        _uiState.update { it.copy(hasPet = hasPet) }
    }
}