package com.google.android.horologist.datalayer.sample.screens.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel

@Composable
fun ApiTestScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel(),
    dogViewModel: DogViewModel = viewModel()
) {
    val giveHeartResult = dogViewModel.giveHeartResult.collectAsState().value
    val userFullInfo = userViewModel.userFullInfo.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // User data reset 버튼
        Button(
            onClick = { userViewModel.resetUserData(userViewModel.userId.value ?: 0L) },  // 예제: userId 1로 설정
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "유저 정보 삭제")
        }

        // Heart 업데이트 버튼
        Button(
            onClick = { userViewModel.updateHeartValue(10) },  // 예제: 10씩 추가
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "밥 얻기")
        }

        // 걸음 수 업데이트 시작 버튼
        Button(
            onClick = { userViewModel.startUpdatingStepsEveryMinute() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "걸음 수 업데이트")
        }

        // 거리 업데이트 시작 버튼
        Button(
            onClick = { userViewModel.startUpdatingDistanceEveryMinute() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "거리 업데이트")
        }

        // User 정보를 가져오는 버튼
        Button(
            onClick = { userViewModel.fetchUserFullInfo(userViewModel.userId.value ?: 0L) }, // 0L은 기본값, 실제 사용 시 userId가 필요
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "유저 정보")
        }

        // 유저 정보 표시
        userFullInfo?.let { info ->
            Text(
                text = "User Info: ${info.user_info.email}, Steps: ${info.state_info?.steps}, Couple ID: ${info.couple_info?.couple_id}",
                modifier = Modifier.padding(top = 8.dp)
            )
        } ?: Text(text = "No user info available", modifier = Modifier.padding(top = 8.dp))

        // 강아지에게 하트 주기 버튼
        Button(
            onClick = { dogViewModel.giveHeartToDog(5) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "강아지 먹이 주기")
        }

        // 하트 주기 결과 표시
        giveHeartResult?.let { result ->
            Text(
                text = if (result.success) {
                    "Hearts given! Updated satiety: ${result.updatedSatiety}"
                } else {
                    "Error: ${result.message}"
                },
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 강아지 위치 업데이트 버튼
        Button(
            onClick = { dogViewModel.updateDogPosition() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "강아지 위치 변경")
        }
    }
}