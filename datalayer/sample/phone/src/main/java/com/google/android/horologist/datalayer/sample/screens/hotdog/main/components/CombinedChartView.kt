package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import android.graphics.Color as AndroidColor
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.horologist.datalayer.sample.ui.theme.BluePurple
import com.google.android.horologist.datalayer.sample.ui.theme.LightBlue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


@Composable
fun CombinedBarChart(steps: List<Int>, hours: List<Int>, monthLabels: List<String>) {
    val stepsMaxValue = steps.maxOrNull()?.toFloat() ?: 1f
    val hoursMaxValue = hours.maxOrNull()?.toFloat() ?: 1f

    val monthLabelsFormatted = monthLabels.map { month ->
        val calendar = Calendar.getInstance().apply {
            time = SimpleDateFormat("MM", Locale.getDefault()).parse(month) ?: time
        }
        "${calendar.get(Calendar.MONTH) + 1}월"
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

                // 왼쪽 Y축: Steps
                axisLeft.apply {
                    axisMinimum = -0.5f
                    axisMaximum = stepsMaxValue * 1.2f
                    textColor = BluePurple.toArgb()
                    setDrawGridLines(false)
                    setLabelCount(2, true)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${String.format("%,d", value.toInt())}걸음"
                        }
                    }
                }

                // 오른쪽 Y축: Hours
                axisRight.apply {
                    axisMinimum = 0f
                    axisMaximum = hoursMaxValue * 1.2f
                    textColor = LightBlue.toArgb()
                    setDrawGridLines(false)
                    setLabelCount(2, true)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${value.toInt()}시간"
                        }
                    }
                }

                // X축 설정
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = AndroidColor.BLACK
                    valueFormatter = IndexAxisValueFormatter(monthLabelsFormatted)
                    labelCount = monthLabelsFormatted.size
                    axisMinimum = 0f  // 최소값 설정
                    axisMaximum = monthLabelsFormatted.size.toFloat()  // 최대값 설정
                    granularity = 1f  // 간격 설정
                    setCenterAxisLabels(true)  // 라벨 가운데 정렬
                }

                legend.isEnabled = true
            }
        },
        update = { barChart ->
            val stepsEntries = steps.mapIndexed { index, value ->
                BarEntry((index + 0.5f), value.toFloat())
            }
            val hoursEntries = hours.mapIndexed { index, value ->
                BarEntry((index + 0.5f), value.toFloat())
            }

            val stepsDataSet = BarDataSet(stepsEntries, "걸음").apply {
                color = BluePurple.toArgb()
                valueTextColor = BluePurple.toArgb()
                valueTextSize = 10f
                setDrawValues(false)
            }

            val hoursDataSet = BarDataSet(hoursEntries, "시간").apply {
                color = LightBlue.toArgb()
                valueTextColor = LightBlue.toArgb()
                valueTextSize = 10f
                setDrawValues(false)
                axisDependency = YAxis.AxisDependency.RIGHT
            }

            val barData = BarData(stepsDataSet, hoursDataSet)
            barData.barWidth = 0.3f

            val groupSpace = 0.4f
            barData.groupBars(0f, groupSpace, 0f)

            barChart.data = barData
            barChart.setVisibleXRangeMaximum(monthLabelsFormatted.size.toFloat())  // 보여질 최대 범위 설정
            barChart.invalidate()

            // 클릭 리스너 수정
            barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    // 모든 데이터셋의 값을 먼저 숨김
                    stepsDataSet.setDrawValues(false)
                    hoursDataSet.setDrawValues(false)

                    // 클릭된 데이터셋만 값을 표시
                    val clickedDataSetIndex = h?.dataSetIndex ?: return
                    when (clickedDataSetIndex) {
                        0 -> { // steps
                            stepsDataSet.setDrawValues(true)
                            stepsDataSet.valueFormatter = object : ValueFormatter() {
                                override fun getFormattedValue(value: Float): String {
                                    return "${String.format("%,d", value.toInt())}걸음"

                                }
                            }
                        }
                        1 -> { // hours
                            hoursDataSet.setDrawValues(true)
                            hoursDataSet.valueFormatter = object : ValueFormatter() {
                                override fun getFormattedValue(value: Float): String {
                                    return "${value.toInt()}시간"
                                }
                            }
                        }
                    }
                    barChart.highlightValue(h)
                    barChart.invalidate()
                }

                override fun onNothingSelected() {
                    stepsDataSet.setDrawValues(false)
                    hoursDataSet.setDrawValues(false)
                    barChart.highlightValue(null)
                    barChart.invalidate()
                }
            })
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    )
}