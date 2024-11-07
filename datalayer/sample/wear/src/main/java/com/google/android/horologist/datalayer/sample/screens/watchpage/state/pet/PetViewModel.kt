package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.data.preferences.FeedingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// PetViewModel.kt
@HiltViewModel
class PetViewModel @Inject constructor( private val feedingPreferences: FeedingPreferences) : ViewModel() {
    private val _uiState = MutableStateFlow(PetUiState())
    val uiState: StateFlow<PetUiState> = _uiState.asStateFlow()

    // 오늘의 먹이 주기 횟수를 State로 관리
    private val _todayFeedingCount = MutableStateFlow(0) // 초기값 0으로 설정
    val todayFeedingCount: StateFlow<Int> = _todayFeedingCount.asStateFlow() // StateFlow로 변환

    // 포만도에 따른 경험치 배율 계산
    private fun getExpMultiplier(satiety: Int): Float = when {
        satiety < 25 -> 0.5f
        satiety < 50 -> 1.0f
        satiety < 75 -> 1.5f
        else -> 2.0f
    }

    // 레벨업에 필요한 경험치 계산
    private fun getRequiredExpForLevel(level: Int): Int = when {
        level <= 10 -> 100 * level
        level <= 20 -> 1000 + (200 * (level - 10))
        level <= 30 -> 3000 + (300 * (level - 20))
        else -> 6000 // 최대 레벨
    }

    // 강아지 이름 업데이트 함수
    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    init {
        // 현재 먹이 주기 횟수 로드
        viewModelScope.launch {
            feedingPreferences.todayFeedingCount.collect { count ->
                _todayFeedingCount.value = count
            }
        }
    }

    // 먹이 주기 함수
    suspend fun tryFeed(): Boolean {
        return if (feedingPreferences.canFeedToday()) {
            feedingPreferences.recordFeeding()
            true
        } else {
            false
        }
    }

    // 현재 먹이 주기 상태 로그 출력
    fun checkFeedingStatus() {
        viewModelScope.launch {
            feedingPreferences.logCurrentState()
        }
    }

    // 먹이 주기 횟수 리셋 함수
    fun resetFeedingCount() {
        viewModelScope.launch {
            feedingPreferences.resetFeeding()
            // 상태 업데이트
            _todayFeedingCount.value = 0
        }
    }

    // 경험치 추가 함수
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

    // 포만감 업데이트 함수
    fun updateSatiety(amount: Int) {
        _uiState.update { currentState ->
            val newSatiety = (currentState.satiety + amount).coerceIn(0, 100)  // 0~100 사이로 제한
            println("PetViewModel - Updating satiety: $newSatiety")  // 업데이트 로그
            currentState.copy(satiety = newSatiety)
        }
    }

    // 디버그용 - 현재 경험치 정보 출력
    fun printExpInfo() {
        val state = _uiState.value
        val nextLevelExp = getRequiredExpForLevel(state.level)
        val multiplier = getExpMultiplier(state.satiety)
        println("""
            현재 상태:
            레벨: ${state.level}
            경험치: ${state.exp}/$nextLevelExp
            포만도: ${state.satiety}
            경험치 배율: x$multiplier
        """.trimIndent())
    }
}