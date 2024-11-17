package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun StatBar(steps: List<Int>, hours: List<Int>, hearts: List<Int>) {
    // X축에 표시할 최근 8개월의 월 생성 (숫자 형식, 공통으로 사용)
    val monthLabels = mutableListOf<String>()
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM", Locale.getDefault())
    repeat(9) {
        monthLabels.add(dateFormat.format(calendar.time))
        calendar.add(Calendar.MONTH, -1)
    }
    monthLabels.reverse()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CombinedBarChart(steps = steps, hours = hours, monthLabels = monthLabels)
        HeartsBarChart(hearts = hearts, monthLabels = monthLabels)
    }
}