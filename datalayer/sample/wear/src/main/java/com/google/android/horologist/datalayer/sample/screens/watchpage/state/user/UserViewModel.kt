package com.google.android.horologist.datalayer.sample.screens.watchpage.state.user

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    fun updateCoupleMatched(isMatched: Boolean) {
        _uiState.update { it.copy(isCoupleMatched = isMatched) }
    }

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
        println("UserViewModel - hasPet 업데이트 시도: $hasPet") // 업데이트 시작 로그
        _uiState.update { it.copy(hasPet = hasPet) }
        println("UserViewModel - hasPet 업데이트 완료: ${_uiState.value.hasPet}") // 업데이트 완료 로그
    }

    // 사용자 활동 상태 업데이트 함수들
    fun updateEatingStatus(isEating: Boolean) {
        _uiState.update { it.copy(eating = isEating) }
    }

    fun updateWorkingStatus(isWorking: Boolean) {
        _uiState.update { it.copy(working = isWorking) }
    }

    fun updateCommutingStatus(isCommuting: Boolean) {
        _uiState.update { it.copy(commuting = isCommuting) }
    }

    // 상대방 활동 상태 업데이트 함수들
    fun updateRecipientEatingStatus(isEating: Boolean) {
        _uiState.update { it.copy(eatingRecipient = isEating) }
    }

    fun updateRecipientWorkingStatus(isWorking: Boolean) {
        _uiState.update { it.copy(workingRecipient = isWorking) }
    }

    fun updateRecipientCommutingStatus(isCommuting: Boolean) {
        _uiState.update { it.copy(commutingRecipient = isCommuting) }
    }

    fun updateRecipientEmoji(nextEmoji: String?) {
        _uiState.update { it.copy(emoji = nextEmoji) }
    }
}