package com.google.android.horologist.datalayer.sample.data.preferences.strategy

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeRestrictionStrategy @Inject constructor() {
    private val _currentType = MutableStateFlow(TimeRestrictionType.DAILY)
    val currentType: StateFlow<TimeRestrictionType> = _currentType

    // 공통 포맷터 사용
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun setType(type: TimeRestrictionType) {
        _currentType.value = type
    }

    fun isNewPeriod(lastDateTime: LocalDateTime?, currentDateTime: LocalDateTime): Boolean {
        return when (_currentType.value) {
            TimeRestrictionType.DAILY -> {
                lastDateTime == null ||
                        lastDateTime.toLocalDate() != currentDateTime.toLocalDate()
            }
            TimeRestrictionType.MINUTE -> {
                lastDateTime == null ||
                        lastDateTime.plusMinutes(1).isBefore(currentDateTime)
            }
        }
    }

    fun getFormattedDateTime(dateTime: LocalDateTime): String {
        // 항상 전체 날짜/시간 정보를 저장
        return dateTime.format(formatter)
    }

    fun parseDateTime(dateTimeStr: String): LocalDateTime {
        // 저장된 전체 날짜/시간 정보를 파싱
        return LocalDateTime.parse(dateTimeStr, formatter)
    }

    val periodName: String
        get() = when (_currentType.value) {
            TimeRestrictionType.DAILY -> "하루"
            TimeRestrictionType.MINUTE -> "1분"
        }
}