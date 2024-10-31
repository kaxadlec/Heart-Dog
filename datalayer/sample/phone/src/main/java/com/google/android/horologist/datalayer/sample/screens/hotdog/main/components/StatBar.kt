package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import android.graphics.Color as AndroidColor // MPAndroidChart Color와 Compose Color 충돌 방지
import android.widget.FrameLayout
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.google.android.horologist.datalayer.sample.ui.theme.BluePurple
import com.google.android.horologist.datalayer.sample.ui.theme.LightBlue
import com.google.android.horologist.datalayer.sample.ui.theme.BrightRed

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
        CombinedChartView(steps = steps, hours = hours, monthLabels = monthLabels)
        Spacer(modifier = Modifier.height(12.dp)) // 간격 추가
        HeartsBarChart(hearts = hearts, monthLabels = monthLabels)
    }
}

@Composable
fun CombinedChartView(steps: List<Int>, hours: List<Int>, monthLabels: List<String>) {
    val context = LocalContext.current

    val lineMaxValue = maxOf(steps.maxOrNull() ?: 0, hours.maxOrNull() ?: 0).toFloat()

    AndroidView(
        factory = { ctx ->
            CombinedChart(ctx).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                setDrawGridBackground(false)
                description.isEnabled = false

                axisLeft.apply {
                    axisMinimum = 0f
                    axisMaximum = lineMaxValue * 1.2f
                    textColor = AndroidColor.BLUE
                    labelCount = 1
                    setDrawGridLines(false)
                }

                axisRight.isEnabled = false

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = AndroidColor.BLACK
                    valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(monthLabels)
                    labelCount = monthLabels.size // 공통 X축 라벨 개수 설정
                }

                legend.isEnabled = false
            }
        },
        update = { combinedChart ->
            val stepsEntries = steps.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat()) }
            val stepsDataSet = LineDataSet(stepsEntries, "Steps").apply {
                color = BluePurple.toArgb()
                lineWidth = 2.5f
                setCircleColor(BluePurple.toArgb())
                circleRadius = 3.5f
                setDrawValues(false)
            }

            val hoursMax = hours.maxOrNull()?.toFloat() ?: 1f
            val hoursScaleFactor = lineMaxValue / hoursMax
            val hoursEntries = hours.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat() * hoursScaleFactor) }
            val hoursDataSet = LineDataSet(hoursEntries, "Hours").apply {
                color = LightBlue.toArgb()
                lineWidth = 2.5f
                setCircleColor(LightBlue.toArgb())
                circleRadius = 3.5f
                setDrawValues(false)
            }

            val lineData = LineData(stepsDataSet, hoursDataSet)

            val combinedData = CombinedData().apply {
                setData(lineData)
            }

            combinedChart.data = combinedData
            combinedChart.invalidate()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // 높이를 절반으로 줄임
    )
}

@Composable
fun HeartsBarChart(hearts: List<Int>, monthLabels: List<String>) {
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

                    val minValue = 0f
                    val maxValue = hearts.maxOrNull()?.toFloat() ?: 0f

                    // 최솟값, 중간값, 최댓값 라벨 추가
                    axisMinimum = minValue
                    axisMaximum = maxValue
                    granularity = (maxValue - minValue) / 2
                    setLabelCount(3, true)
                }

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = AndroidColor.BLACK
                    valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(monthLabels)
                    labelCount = monthLabels.size // 공통 X축 라벨 개수 설정
                }

                axisRight.isEnabled = false
                legend.isEnabled = false
            }
        },
        update = { barChart ->
            val heartsEntries = hearts.mapIndexed { index, value -> BarEntry(index.toFloat(), value.toFloat()) }
            val heartsDataSet = BarDataSet(heartsEntries, "Heart Rate").apply {
                color = BrightRed.toArgb()
                setDrawValues(false)
            }

            val barData = BarData(heartsDataSet)
            barChart.data = barData
            barChart.invalidate()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) // 그래프의 높이 설정
    )
}
