// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

@Composable
fun PetTab(
    modifier: Modifier = Modifier,
    onNavigateToFeed: () -> Unit,
    onNavigateToCall: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val userState by userViewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "하트: ${userState.heart}", color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToFeed) {
            Text("하트 먹이기")
        }

        Button(onClick = onNavigateToCall) {
            Text("호출하기")
        }
    }
}