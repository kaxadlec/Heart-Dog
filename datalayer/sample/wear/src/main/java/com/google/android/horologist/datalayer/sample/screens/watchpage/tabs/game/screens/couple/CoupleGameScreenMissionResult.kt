package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple

import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.R


@Composable
fun CoupleGameScreenMissionResult(
    onBack: () -> Unit // 추가: onBack 콜백
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val icon: Painter = painterResource(id = R.drawable.redheart)
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = "당신의 심박수 : ",
            fontSize = 10.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "상대의 심박수 : ",
            fontSize = 10.sp,
            color = Color.Black
        )
    }
}