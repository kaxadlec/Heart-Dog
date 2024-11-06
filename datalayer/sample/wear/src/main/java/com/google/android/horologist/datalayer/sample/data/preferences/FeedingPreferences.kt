package com.google.android.horologist.datalayer.sample.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

private val Context.feedingDataStore: DataStore<Preferences> by preferencesDataStore(name = "feeding")

@Singleton
class FeedingPreferences @Inject constructor(private val context: Context) {
    private val lastFeedingDateKey = stringPreferencesKey("last_feeding_date")
    private val feedingCountKey = intPreferencesKey("feeding_count")

    // 오늘의 먹이 주기 횟수 가져오기
    val todayFeedingCount: Flow<Int> = context.feedingDataStore.data
        .map { preferences ->
            val lastFeedingDate = preferences[lastFeedingDateKey]
            val today = LocalDate.now().toString()

            if (lastFeedingDate != today) {
                // 날짜가 바뀌었으면 0 반환
                0
            } else {
                // 같은 날이면 현재 카운트 반환
                preferences[feedingCountKey] ?: 0
            }
        }

    // 먹이 주기 기록하기
    suspend fun recordFeeding() {
        context.feedingDataStore.edit { preferences ->
            val today = LocalDate.now().toString()
            val lastFeedingDate = preferences[lastFeedingDateKey]

            if (lastFeedingDate != today) {
                // 날짜가 바뀌었으면 카운트 1로 리셋
                preferences[lastFeedingDateKey] = today
                preferences[feedingCountKey] = 1
                println("FeedingPreferences - 새로운 날짜 기록, 카운트: 1")
            } else {
                // 같은 날이면 카운트 증가
                val currentCount = preferences[feedingCountKey] ?: 0
                if (currentCount < 10) {  // 최대 10회까지만 허용
                    preferences[feedingCountKey] = currentCount + 1
                    println("FeedingPreferences - 급여 횟수 증가: ${currentCount + 1}")
                }
            }
        }
    }

    // 오늘 더 먹이를 줄 수 있는지 확인
    suspend fun canFeedToday(): Boolean {
        return context.feedingDataStore.data.map { preferences ->
            val lastFeedingDate = preferences[lastFeedingDateKey]
            val today = LocalDate.now().toString()

            if (lastFeedingDate != today) 0
            else preferences[feedingCountKey] ?: 0
        }.first() < 10
    }

    // 현재 상태 로그 출력 함수
    suspend fun logCurrentState() {
        context.feedingDataStore.data.first().let { preferences ->
            val lastDate = preferences[lastFeedingDateKey] ?: "없음"
            val count = preferences[feedingCountKey] ?: 0
            println("FeedingPreferences - 마지막 급여일: $lastDate, 오늘 급여 횟수: $count")
        }
    }

    // 하루동안 최대 먹이주기 제한 데이터 초기화 함수
    suspend fun resetFeeding() {
        context.feedingDataStore.edit { preferences ->
            preferences.clear() // 모든 데이터 삭제
            println("FeedingPreferences - 데이터 초기화 완료")
        }
    }

}