package com.google.android.horologist.datalayer.sample.screens.watchpage.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ExperienceArcs(
    modifier: Modifier = Modifier,
    leftProgress: Float,
    rightProgress: Float,
    leftColor: Color = Color(0xFFD32F2F),
    rightColor: Color = Color(0xFF388E3C),
    strokeWidth: Float = 15f // dp
) {
    Canvas(modifier = modifier) {
        val strokeWidthPx = strokeWidth.dp.toPx()
        val radius = size.minDimension / 2

        // 왼쪽 반원 (빨간색)
        drawArc(
            color = leftColor,
            startAngle = -90f,
            sweepAngle = -(180f * leftProgress),
            useCenter = false,
            topLeft = center.copy(
                x = center.x - radius,
                y = center.y - radius
            ),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Butt)
        )

        // 오른쪽 반원 (연두색)
        drawArc(
            color = rightColor,
            startAngle = -90f,
            sweepAngle = 180f * rightProgress,
            useCenter = false,
            topLeft = center.copy(
                x = center.x - radius,
                y = center.y - radius
            ),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Butt)
        )
    }
}