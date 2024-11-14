package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.ui.theme.ExpColor
import com.google.android.horologist.datalayer.sample.ui.theme.HeartColor
import com.google.android.horologist.datalayer.sample.ui.theme.bgColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.LocalDogViewModel

@Composable
fun ProgressBar() {

    val dogViewModel = LocalDogViewModel.current
    var dogDetails = dogViewModel.dogDetails.collectAsState().value

    LaunchedEffect(dogViewModel.dogDetails) {
        dogViewModel.dogDetails.collect { dogDetails ->
            Log.d("ProgressBar", "강아지 상태 변화: $dogDetails")
        }
    }

    dogDetails?.let { dog ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Lv.${dog.level} ${dog.name}",
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
                    text = "${dog.satiety}",
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
                            .fillMaxWidth(dog.satiety / 100f)
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
                    text = "${dog.currentExp}",
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
                            .fillMaxWidth(dog.currentExp / dog.maxExp.toFloat())
                            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                            .background(ExpColor, shape = RoundedCornerShape(12.dp))
                    )
                }
            }

            // 경험치를 더 모으면 레벨업!
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${dog.maxExp - dog.currentExp}을 더 모으면 레벨업!",
                    color = textColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )
            }

        }
    } ?: run {
        Text(
            text = "강아지가 없다",
            fontSize = 32.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
        )
        Log.d("ProgressBar", "No dog details available")
    }
}