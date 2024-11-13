// SettingsTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.CircleIconButton
import com.google.android.horologist.datalayer.sample.R

@Composable
fun SettingsTab(
    modifier: Modifier = Modifier,
    onNavigateToGuide: () -> Unit,
    onNavigateToReset: () -> Unit,
    onNavigateToLogout: () -> Unit,
    paddingRatio: Float = 0.135f
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val dynamicPadding = screenWidth * paddingRatio


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dynamicPadding)
        ) {
            // 상단 버튼
            CircleIconButton(
                text = "사용방법",
                onClick = onNavigateToGuide,
                iconResId = R.drawable.icon_guide,  // 가이드 아이콘
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // 좌하단 버튼
            CircleIconButton(
                text = "로그아웃",
                onClick = onNavigateToLogout,
                iconResId = R.drawable.icon_logout,  // 로그아웃 아이콘
                iconSizeRatio = 0.16f,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            // 우하단 버튼
            CircleIconButton(
                text = "초기화",
                onClick = onNavigateToReset,
                iconResId = R.drawable.icon_reset,  // 초기화 아이콘
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}