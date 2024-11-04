package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.ContentType

@Composable
fun DynamicContentDisplay(contentType: ContentType, heartValue: Int = 75, expValue: Int = 50,
                          dist: Int = 1202, hour: Int = 4994, heart: Int = 15000,
                          steps: List<Int> = listOf(60100, 52000, 81500, 41800, 32200, 52500, 93000, 32700, 23200),
                          hours: List<Int> = listOf(12, 21, 13, 14, 15, 6, 17, 18, 9),
                          hearts: List<Int> = listOf(70, 45, 120, 85, 60, 55, 100, 105, 70)
                          ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 이름 표시
        when (contentType) {
            ContentType.ABOUT -> {
                ProgressBar(heartValue, expValue)
            }
            ContentType.STATS -> {
                StatData(dist, hour, heart)
                StatBar(steps, hours, hearts)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DynamicContentDisplayPreview() {
    DynamicContentDisplay(contentType = ContentType.ABOUT)
}