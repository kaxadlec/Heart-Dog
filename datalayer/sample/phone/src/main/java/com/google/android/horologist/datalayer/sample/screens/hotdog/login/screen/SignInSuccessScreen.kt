package com.google.android.horologist.datalayer.sample.screens.hotdog.login.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SignInSuccessScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    email: String,
    createdAt: String,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "로그인 성공!!")
        Text(text = "Email $email")
        Text(text = "Created at $createdAt")
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            onClick = onClick
        ) {
            Text("Continue")
        }
    }
}