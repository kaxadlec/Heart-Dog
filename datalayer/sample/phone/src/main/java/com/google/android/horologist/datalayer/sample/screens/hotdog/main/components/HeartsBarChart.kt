package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import android.graphics.Color as AndroidColor
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.horologist.datalayer.sample.ui.theme.BrightRed
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun HeartsBarChart(hearts: List<Int>, monthLabels: List<String>) {
    val context = LocalContext.current

    // 월 이름을 "3월", "4월"과 같은 형식으로 변경
    val monthLabelsFormatted = monthLabels.map { month ->
        val calendar = Calendar.getInstance().apply {
            time = SimpleDateFormat("MM", Locale.getDefault()).parse(month) ?: time
        }
        "${calendar.get(Calendar.MONTH) + 1}월" // "3월", "4월" 형식으로 변경
    }

    AndroidView(
        factory = { ctx ->
            BarChart(ctx).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                setDrawGridBackground(false)
                description.isEnabled = false

                axisLeft.apply {
                    axisMinimum = 0f
                    textColor = AndroidColor.RED
                    setDrawGridLines(false)
                    val maxValue = hearts.maxOrNull()?.toFloat() ?: 0f
                    axisMaximum = maxValue * 1.1f // 최댓값이 충분히 높이 표시되도록 조정
                    granularity = (maxValue / 2).coerceAtLeast(1f)
                    setLabelCount(3, true)
                    setDrawLabels(false) // 단위만 보여주도록 값은 숨김
                }

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = AndroidColor.BLACK
                    valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(monthLabelsFormatted)
                    labelCount = monthLabelsFormatted.size
                }

                axisRight.isEnabled = false
            }
        },
        update = { barChart ->
            val heartsEntries = hearts.mapIndexed { index, value -> BarEntry(index.toFloat(), value.toFloat()) }
            val heartsDataSet = BarDataSet(heartsEntries, "하트").apply {
                color = BrightRed.toArgb()
                setDrawValues(false) // 평소에는 숫자가 보이지 않도록 설정
            }

            val barData = BarData(heartsDataSet)
            barChart.data = barData
            barChart.invalidate()

            // 클릭 리스너 추가하여 클릭 시에만 해당 값을 표시
            barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    // 선택된 값에 단위 추가하여 표시하는 커스텀 포맷터 생성
                    heartsDataSet.setDrawValues(true)
                    heartsDataSet.valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${value.toInt()}개"
                        }
                    }
                    barChart.highlightValue(h) // 선택된 값만 하이라이트
                    barChart.invalidate() // 그래프 갱신
                }

                override fun onNothingSelected() {
                    heartsDataSet.setDrawValues(false) // 선택 해제 시 숫자 숨김
                    barChart.highlightValue(null) // 모든 하이라이트 해제
                    barChart.invalidate() // 그래프 갱신
                }
            })
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 32.dp, end = 24.dp) // 그래프가 약간 뒤로 오도록 왼쪽 패딩 추가
    )
}
