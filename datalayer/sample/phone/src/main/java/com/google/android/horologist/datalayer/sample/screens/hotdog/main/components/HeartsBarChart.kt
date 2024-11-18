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
    val heartsMaxValue = hearts.maxOrNull()?.toFloat() ?: 0f

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
                setScaleEnabled(false)
                setPinchZoom(false)

                axisLeft.apply {
                    axisMinimum = 0f
                    axisMaximum = heartsMaxValue * 1.1f
                    textColor = AndroidColor.RED
                    setDrawGridLines(false)
                    granularity = (heartsMaxValue / 2).coerceAtLeast(1f)
                    setLabelCount(2, true)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${value.toInt()}개"
                        }
                    }
                }

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = AndroidColor.BLACK
                    valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(monthLabelsFormatted)
                    labelCount = monthLabelsFormatted.size
                    axisMinimum = 0f
                    axisMaximum = monthLabelsFormatted.size.toFloat()
                    granularity = 1f
                    setCenterAxisLabels(true)
                    yOffset = 5f
                }

                axisRight.isEnabled = false

                legend.isEnabled = true
                extraBottomOffset = 5f
            }
        },
        update = { barChart ->
            val heartsEntries = hearts.mapIndexed { index, value ->
                BarEntry((index + 0.5f), value.toFloat())
            }

            val heartsDataSet = BarDataSet(heartsEntries, "하트").apply {
                color = BrightRed.toArgb()
                valueTextColor = BrightRed.toArgb()
                valueTextSize = 10f
                setDrawValues(false)
            }

            val barData = BarData(heartsDataSet).apply {
                barWidth = 0.6f
            }

            barChart.apply {
                data = barData
                setFitBars(true)
                setVisibleXRangeMaximum(monthLabelsFormatted.size.toFloat())
                invalidate()
            }

            barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    heartsDataSet.setDrawValues(true)
                    heartsDataSet.valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${value.toInt()}개"
                        }
                    }
                    barChart.highlightValue(h)
                    barChart.invalidate()
                }

                override fun onNothingSelected() {
                    heartsDataSet.setDrawValues(false)
                    barChart.highlightValue(null)
                    barChart.invalidate()
                }
            })
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 32.dp, end = 24.dp)
    )
}