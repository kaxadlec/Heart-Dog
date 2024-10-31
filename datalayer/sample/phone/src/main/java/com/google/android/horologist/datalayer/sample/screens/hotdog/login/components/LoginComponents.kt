package com.google.android.horologist.datalayer.sample.screens.hotdog.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.Matching

@Composable
fun LoginComponent(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.main_logo),
            contentDescription = null,
            modifier = Modifier
                .width(350.dp)
                .height(350.dp)
                .offset(y = (-50).dp),
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.login_button),
            contentDescription = null,
            modifier = Modifier
                .width(370.dp)
                .height(120.dp)
                .offset(y = 170.dp)
                .clickable(
                    indication = null, // 클릭 피드백 제거
                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
                ) {
                    navController.navigate(Matching)
                },
            contentScale = ContentScale.Fit
        )
    }
}