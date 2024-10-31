package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// PetViewModel.kt
@HiltViewModel
class PetViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PetUiState())
    val uiState: StateFlow<PetUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun addExp(amount: Int) {
        _uiState.update { currentState ->
            val newExp = currentState.exp + amount
            val expForNextLevel = 100   // 100exp 마다 레벨업하는 것으로 일단 설정
            val levelUps = newExp / expForNextLevel
            val remainingExp = newExp % expForNextLevel

            currentState.copy(
                level = currentState.level + levelUps,
                exp = remainingExp
            )
        }
    }

    fun updateSatiety(amount: Int) {
        _uiState.update { currentState ->
            val newSatiety = (currentState.satiety + amount).coerceIn(0, 100)  // 0~100 사이로 제한
            currentState.copy(satiety = newSatiety)
        }
    }
}