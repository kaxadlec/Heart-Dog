// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

@Composable
fun PetTab(
    modifier: Modifier = Modifier,
    onNavigateToFeed: () -> Unit,
    onNavigateToCall: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel(),
    petViewModel: PetViewModel
) {
    val userState by userViewModel.uiState.collectAsStateWithLifecycle()
    val petState by petViewModel.uiState.collectAsStateWithLifecycle()

    // 상태 변화 로그
    LaunchedEffect(petState.satiety) {
        println("PetTab - Satiety Changed: ${petState.satiety}")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "하트: ${userState.heart}", color = Color.Black)
        Text(text = "현재 포만도: ${petState.satiety}", color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (userState.heart > 0) {  // 하트가 있을 때만 실행
                    userViewModel.updateHeart(userState.heart - 1)  // 하트 1 감소
                    petViewModel.updateSatiety(5)  // 포만도 5 증가
                }
            }, enabled = userState.heart > 0  // 하트가 0이면 버튼 비활성화
        ) {
            Text("하트 먹이기")
        }

        Button(onClick = onNavigateToCall) {
            Text("호출하기")
        }
    }
}