package com.google.android.horologist.datalayer.sample.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.horologist.datalayer.sample.data.preferences.strategy.TimeRestrictionStrategy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

private val Context.feedingDataStore: DataStore<Preferences> by preferencesDataStore(name = "feeding")

@Singleton
class FeedingPreferences @Inject constructor(
    private val context: Context,
    private val timeStrategy: TimeRestrictionStrategy
) {
    private val lastFeedingDateKey = stringPreferencesKey("last_feeding_date")
    private val feedingCountKey = intPreferencesKey("feeding_count")

    val todayFeedingCount: Flow<Int> = context.feedingDataStore.data
        .map { preferences ->
            val lastFeedingDateStr = preferences[lastFeedingDateKey]
            val now = LocalDateTime.now()

            if (lastFeedingDateStr == null) {
                0
            } else {
                val lastFeedingDate = timeStrategy.parseDateTime(lastFeedingDateStr)
                if (timeStrategy.isNewPeriod(lastFeedingDate, now)) {
                    0
                } else {
                    preferences[feedingCountKey] ?: 0
                }
            }
        }

    suspend fun recordFeeding() {
        context.feedingDataStore.edit { preferences ->
            val now = LocalDateTime.now()
            val lastFeedingDateStr = preferences[lastFeedingDateKey]

            val lastFeedingDate = lastFeedingDateStr?.let { timeStrategy.parseDateTime(it) }

            if (timeStrategy.isNewPeriod(lastFeedingDate, now)) {
                preferences[lastFeedingDateKey] = timeStrategy.getFormattedDateTime(now)
                preferences[feedingCountKey] = 1
                println("FeedingPreferences - 새로운 ${timeStrategy.periodName} 시작, 카운트: 1")
            } else {
                val currentCount = preferences[feedingCountKey] ?: 0
                if (currentCount < 10) {
                    preferences[feedingCountKey] = currentCount + 1
                    println("FeedingPreferences - 급여 횟수 증가: ${currentCount + 1}")
                }
            }
        }
    }

    suspend fun canFeedToday(): Boolean {
        return context.feedingDataStore.data.map { preferences ->
            val lastFeedingDateStr = preferences[lastFeedingDateKey]
            val now = LocalDateTime.now()

            val lastFeedingDate = lastFeedingDateStr?.let { timeStrategy.parseDateTime(it) }

            if (timeStrategy.isNewPeriod(lastFeedingDate, now)) 0
            else preferences[feedingCountKey] ?: 0
        }.first() < 10
    }

    suspend fun logCurrentState() {
        context.feedingDataStore.data.first().let { preferences ->
            val lastDate = preferences[lastFeedingDateKey] ?: "없음"
            val count = preferences[feedingCountKey] ?: 0
            println("FeedingPreferences - 마지막 급여 (${timeStrategy.periodName}): $lastDate, 현재 급여 횟수: $count")
        }
    }

    suspend fun resetFeeding() {
        context.feedingDataStore.edit { preferences ->
            preferences.clear()
//            println("FeedingPreferences - 데이터 초기화 완료")
        }
    }
}