package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.ui.theme.ExpColor
import com.google.android.horologist.datalayer.sample.ui.theme.HeartColor
import com.google.android.horologist.datalayer.sample.ui.theme.bgColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun ProgressBar(heartValue: Int, expValue: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lv.30        몽실이",
            fontSize = 32.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 하트 게이지 바
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Heart",
                color = textColor,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            Text(
                text = "$heartValue",
                color = textColor,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(2f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .weight(7f)
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .background(bgColor, shape = RoundedCornerShape(12.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(heartValue / 100f)
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                        .background(HeartColor, shape = RoundedCornerShape(12.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 경험치 게이지 바
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "EXP",
                color = textColor,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            Text(
                text = "$expValue",
                color = textColor,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(2f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .weight(7f)
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .background(bgColor, shape = RoundedCornerShape(12.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(expValue / 100f)
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                        .background(ExpColor, shape = RoundedCornerShape(12.dp))
                )
            }
        }
    }
}
