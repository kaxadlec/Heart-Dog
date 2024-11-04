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
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.Screen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.GameTabScreen


@Composable
fun CoupleGameScreenMissionResult(
    onBack: () -> Unit,
    navController: NavController
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

        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "당신의 최고 심박수 : ",
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "상대의 최고 심박수 : ",
            fontSize = 12.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        androidx.wear.compose.material.Button(
            onClick = {
                // 게임화면 밖으로 나가기
                navController.popBackStack(
                    route = GameTabScreen.Main.route,
                    inclusive = false
                )
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .size(width = 80.dp, height = 30.dp)
        ) {
            Text(
                text = "확인",
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}