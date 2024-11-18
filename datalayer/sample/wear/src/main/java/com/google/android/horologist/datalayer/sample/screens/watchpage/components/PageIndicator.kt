package com.google.android.horologist.datalayer.sample.screens.watchpage.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorCount: Int = 5,
    indicatorSizeRatio: Float = 0.03f,
    curveRadiusRatio: Float = 0.6f,
    paddingBottomRatio: Float = 0.28f,
    activeColor: Color = Color(0xFFFF9800),
    inactiveColor: Color = Color(0xFFFFECB3),
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val indicatorSize = screenWidth * indicatorSizeRatio // 페이지 인디케이터 크기
    val paddingBottom = screenWidth * paddingBottomRatio // 페이지 인디케이터와 화면 하단 간격
    val density = LocalDensity.current
    val curveRadiusPx = with(density) { (screenWidth * curveRadiusRatio).toPx() }
    val indicatorSizePx = with(density) { indicatorSize.toPx() }

    Box(
        modifier = modifier.padding(bottom = paddingBottom)
    ) {
        Canvas(modifier = Modifier.size(screenWidth * curveRadiusRatio * 2)) {
            val angleBetweenIndicators = -20f / (indicatorCount - 1)
            val center = size.center
            repeat(indicatorCount) { index ->
                val angle = 10f + (angleBetweenIndicators * index)
                rotate(degrees = angle, pivot = center) {
                    drawCircle(
                        color = if (pagerState.currentPage == index) activeColor else inactiveColor,
                        radius = indicatorSizePx / 2,
                        center = center.copy(
                            x = center.x,
                            y = center.y + curveRadiusPx
                        )
                    )
                }
            }
        }
    }
}
