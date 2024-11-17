package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.ui.theme.BluePurple
import com.google.android.horologist.datalayer.sample.ui.theme.BrightRed
import com.google.android.horologist.datalayer.sample.ui.theme.LightBlue
import com.google.android.horologist.datalayer.sample.ui.theme.textColor
import java.text.NumberFormat
import java.util.Locale

@Composable
fun StatData(dist: Int, hour: Int, heart: Int) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US).apply {
        // 정수 형식으로 패딩 없는 숫자 설정
        isGroupingUsed = true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // 첫 번째 줄: 함께한 걸음, 함께한 시간, 누적 하트
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(
                text = "함께한 걸음 수",
                fontSize = 16.sp,
                color = BluePurple,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "함께한 시간",
                fontSize = 16.sp,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "누적 하트",
                fontSize = 16.sp,
                color = BrightRed,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        // 두 번째 줄: 거리 값, 시간 값, 하트 값
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(
                text = "${numberFormat.format(dist)} 걸음",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "${numberFormat.format(hour)} 시간",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "${numberFormat.format(heart)} 개",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
