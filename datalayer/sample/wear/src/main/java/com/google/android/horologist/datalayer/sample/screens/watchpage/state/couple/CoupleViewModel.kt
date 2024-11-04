package com.google.android.horologist.datalayer.sample.screens.watchpage.state.couple

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// CoupleViewModel.kt
@HiltViewModel
class CoupleViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CoupleUiState())
    val uiState: StateFlow<CoupleUiState> = _uiState.asStateFlow()

    fun updateTimeTogether(minutes: Long) {
        _uiState.update { it.copy(timeTogetherMinutes = minutes) }
    }

    // 함께한 시간을 보기 좋게 변환하는 함수
    fun getFormattedTimeTogether(): String {
        val minutes = uiState.value.timeTogetherMinutes
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days > 0 -> "${days}일 ${hours % 24}시간"
            hours > 0 -> "${hours}시간 ${minutes % 60}분"
            else -> "${minutes}분"
        }
    }

    // 시간 추가 함수
    fun addTime(additionalMinutes: Long) {
        _uiState.update {
            it.copy(timeTogetherMinutes = it.timeTogetherMinutes + additionalMinutes)
        }
    }
}