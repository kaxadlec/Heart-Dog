package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import android.graphics.Color as AndroidColor
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.horologist.datalayer.sample.ui.theme.BluePurple
import com.google.android.horologist.datalayer.sample.ui.theme.LightBlue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CombinedChartView(steps: List<Int>, hours: List<Int>, monthLabels: List<String>) {
    val context = LocalContext.current
    val stepsMaxValue = steps.maxOrNull()?.toFloat() ?: 1f
    val hoursMaxValue = hours.maxOrNull()?.toFloat() ?: 1f

    // 월 이름을 "3월", "4월"과 같은 형식으로 변경
    val monthLabelsFormatted = monthLabels.map { month ->
        val calendar = Calendar.getInstance().apply {
            time = SimpleDateFormat("MM", Locale.getDefault()).parse(month) ?: time
        }
        "${calendar.get(Calendar.MONTH) + 1}월"
    }

    AndroidView(
        factory = { ctx ->
            CombinedChart(ctx).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                setDrawGridBackground(false)
                description.isEnabled = false

                // 왼쪽 Y축: Steps
                axisLeft.apply {
                    axisMinimum = 0f
                    axisMaximum = stepsMaxValue * 1.2f
                    textColor = BluePurple.toArgb()
                    setDrawGridLines(false)
                    setLabelCount(1, true)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${String.format("%.2f", value / 1000)} km"
                        }
                    }
                }

                // 오른쪽 Y축: Hours
                axisRight.apply {
                    axisMinimum = 0f
                    axisMaximum = hoursMaxValue * 1.2f
                    textColor = LightBlue.toArgb()
                    setDrawGridLines(false)
                    setLabelCount(1, true)
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
                    valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(monthLabelsFormatted)
                    labelCount = monthLabelsFormatted.size
                }

                legend.isEnabled = false
            }
        },
        update = { combinedChart ->
            val stepsEntries = steps.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat()) }
            val stepsDataSet = LineDataSet(stepsEntries, "Steps").apply {
                color = BluePurple.toArgb()
                lineWidth = 3f
                setCircleColor(BluePurple.toArgb())
                circleRadius = 4f
                setDrawValues(false)
            }

            val hoursEntries = hours.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat()) }
            val hoursDataSet = LineDataSet(hoursEntries, "Hours").apply {
                color = LightBlue.toArgb()
                lineWidth = 3f
                setCircleColor(LightBlue.toArgb())
                circleRadius = 4f
                setDrawValues(false)
                axisDependency = YAxis.AxisDependency.RIGHT // Right Y-axis
            }

            val lineData = LineData(stepsDataSet, hoursDataSet)
            val combinedData = CombinedData().apply { setData(lineData) }

            combinedChart.data = combinedData
            combinedChart.invalidate()

            // 클릭 리스너 추가하여 클릭 시에만 값 표시
            combinedChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    stepsDataSet.setDrawValues(true)
                    hoursDataSet.setDrawValues(true)
                    stepsDataSet.valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return String.format("%.2f km", value / 1000)
                        }
                    }
                    hoursDataSet.valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${value.toInt()}시간"
                        }
                    }
                    combinedChart.highlightValue(h)
                    combinedChart.invalidate()
                }

                override fun onNothingSelected() {
                    stepsDataSet.setDrawValues(false)
                    hoursDataSet.setDrawValues(false)
                    combinedChart.highlightValue(null)
                    combinedChart.invalidate()
                }
            })
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    )
}
