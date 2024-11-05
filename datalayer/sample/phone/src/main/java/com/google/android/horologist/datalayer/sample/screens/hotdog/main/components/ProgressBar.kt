package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.ExpViewModel
import com.google.android.horologist.datalayer.sample.ui.theme.ExpColor
import com.google.android.horologist.datalayer.sample.ui.theme.HeartColor
import com.google.android.horologist.datalayer.sample.ui.theme.bgColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun ProgressBar(
    dogId: Long,
    dogViewModel: DogViewModel = viewModel(),
    expViewModel: ExpViewModel = viewModel()
) {
    val dogInfo by dogViewModel.dogInfo.collectAsState()
    val expInfo by expViewModel.expInfo.collectAsState()

    val maxExp = expInfo?.maxExp ?: 100L // 기본값으로 100 설정

    if (dogInfo == null) {
        dogViewModel.fetchDogById(dogId)
    }

    // dogInfo의 level을 기반으로 maxExp를 가져옴
    dogInfo?.level?.let { level ->
        expViewModel.fetchMaxExpByLevel(level)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Lv.${dogInfo?.level ?: "N/A"} ${dogInfo?.name ?: "Unknown"}",
            fontSize = 32.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 포만도 게이지 바
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "포만도",
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            Text(
                text = "${dogInfo?.satiety ?: "null"}",
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
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
                        .fillMaxWidth((dogInfo?.satiety?.toFloat() ?: 0f) / 100f)
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
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            Text(
                text = "${dogInfo?.currentExp ?: "null"}",
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
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
                        .fillMaxWidth((dogInfo?.currentExp?.toFloat() ?: 0f) / maxExp.toFloat())
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                        .background(ExpColor, shape = RoundedCornerShape(12.dp))
                )
            }
        }

        // 경험치를 더 모으면 레벨업!
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End // 오른쪽 정렬
        ) {
            Text(
                text = "${maxExp - (dogInfo?.currentExp ?: 0)}을 더 모으면 레벨업!",
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )
        }
    }
}
