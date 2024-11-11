package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.sample.data.preferences.FeedingPreferences
import com.google.android.horologist.datalayer.sample.data.preferences.strategy.TimeRestrictionStrategy
import com.google.android.horologist.datalayer.sample.data.preferences.strategy.TimeRestrictionType
import com.google.android.horologist.datalayer.sample.shared.grpc.DogProto
import com.google.android.horologist.datalayer.sample.shared.grpc.DogServiceGrpcKt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// PetViewModel.kt
@HiltViewModel
class PetViewModel @Inject constructor( private val feedingPreferences: FeedingPreferences, private val timeStrategy: TimeRestrictionStrategy, private val registry: WearDataLayerRegistry) : ViewModel() {
    // ----------------------------------상태 관리----------------------------------
    // 기본 UI 상태
    private val _uiState = MutableStateFlow(PetUiState())
    val uiState: StateFlow<PetUiState> = _uiState.asStateFlow()

    // 먹이 주기 횟수 상태
    private val _todayFeedingCount = MutableStateFlow(0) // 초기값 0으로 설정
    val todayFeedingCount: StateFlow<Int> = _todayFeedingCount.asStateFlow() // StateFlow로 변환


    init {
        // 기존 피딩 카운트 초기화
        viewModelScope.launch {
            feedingPreferences.todayFeedingCount.collect { count ->
                _todayFeedingCount.value = count
            }
        }

        // Phone의 데이터를 받아오기 위한 DataStore 설정
        viewModelScope.launch {
            try {
                val dogDataStore: DataStore<DogProto.DogRecord> = registry.protoDataStore(coroutineScope = coroutineScope,
                    serializer = DogRecordSerializer)

                // Phone의 데이터 구독
                dogDataStore.data.collect { dogRecord ->
                    Log.d("WatchPetViewModel", "Phone 데이터 수신: $dogRecord")
                    _uiState.update {
                        it.copy(
                            dogId = dogRecord.dogId.toString(),
                            name = dogRecord.name,
                            level = dogRecord.level,
                            current_exp = dogRecord.currentExp,
                            satiety = dogRecord.satiety,
                            position = dogRecord.position
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("WatchPetViewModel", "Phone 데이터 수신 실패", e)
            }
        }
    }


    // ----------------------------------먹이 주기 관련 함수----------------------------------
    // 먹이 주기 함수
    suspend fun tryFeed(): Boolean {
        return if (feedingPreferences.canFeedToday()) {
            feedingPreferences.recordFeeding()
            // 경험치 추가 로직
            val expToAdd = calculateExpForCurrentSatiety()
            addExp(expToAdd)

            println("먹이를 주었습니다. 포만도: ${_uiState.value.satiety}, 추가된 경험치: $expToAdd")
            true
        } else {
            false
        }
    }

    // 먹이 주기 횟수 초기화
    fun resetFeedingCount() {
        viewModelScope.launch {
            feedingPreferences.resetFeeding()
            // 상태 업데이트
            _todayFeedingCount.value = 0
        }
    }

    // 현재 먹이 주기 상태 로그 출력
    fun checkFeedingStatus() {
        viewModelScope.launch {
            feedingPreferences.logCurrentState()
        }
    }

    // ----------------------------------경험치/레벨 관련 함수----------------------------------
    // 경험치 추가 및 레벨업 처리
    fun addExp(amount: Int) {
        _uiState.update { currentState ->
            val newExp = currentState.current_exp + amount
            val requiredExpForNextLevel = getRequiredExpForLevel(currentState.level)

            // 경험치가 레벨업 기준을 초과하면 레벨업
            var levelUps = 0
            var remainingExp = newExp

            while (remainingExp >= requiredExpForNextLevel && currentState.level + levelUps < 30) {
                levelUps++
                remainingExp -= requiredExpForNextLevel
            }

            currentState.copy(
                level = currentState.level + levelUps,
                current_exp = remainingExp
            )
        }
    }
    // 강아지 레벨에 따른 필요 경험치 계산 함수
    // 강아지 레벨(1~10): 100~1000
    // 강아지 레벨(11~20): 1200 ~ 3000
    // 강아지 레벨(21~30): 3300 ~ 6000
    fun getRequiredExpForLevel(level: Int): Int {
        return when {
            level in 1..10 -> 100 * level // 1~10 레벨은 100
            level in 11..20 -> 1000 + (200 * (level - 10)) // 11~20 레벨은 200
            level in 21..30 -> 3000 + (300 * (level - 20)) // 21~30 레벨은 300
            else -> 6000 // 최대 경험치, 레벨 제한이 있을 경우 고정
        }
    }

    // 포만도에 따른 경험치 배율을 결정하는 함수
    // 포만도에 따라 x0.5, x1, x1.5, x2 경험치 차등 지급
    private fun getExpMultiplier(satiety: Int): Float {
        return when {
            satiety < 25 -> 0.5f
            satiety < 50 -> 1.0f
            satiety < 75 -> 1.5f
            else -> 2.0f
        }
    }

    // 포만도에 따른 경험치 배율을 적용하여 경험치를 계산하는 함수
    private fun calculateExpForCurrentSatiety(): Int {
        val baseExp = 20 // 먹이 하나당 기본 경험치 (원래는 2인데 테스트 위해 20으로 설정)
        val satiety = _uiState.value.satiety // 현재 포만도
        val multiplier = getExpMultiplier(satiety) // 포만도에 따른 배율 적용
        return (baseExp * multiplier).toInt() // 계산된 경험치 반환
    }


    // ----------------------------------기타 상태 업데이트 함수----------------------------------
    // 현재 시간 제한 타입
    val currentTimeRestrictionType = timeStrategy.currentType

    // 시간 제한 타입 변경 함수
    fun setTimeRestrictionType(type: TimeRestrictionType) {
        timeStrategy.setType(type)
        // 타입 변경 시 먹이 주기 횟수 초기화
        resetFeedingCount()
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

    // 포만감 업데이트 함수
    fun updateSatiety(amount: Int) {
        _uiState.update { currentState ->
            val newSatiety = (currentState.satiety + amount).coerceIn(0, 100)  // 0~100 사이로 제한
            println("PetViewModel - Updating satiety: $newSatiety")  // 업데이트 로그
            currentState.copy(satiety = newSatiety)
        }
    }

}