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

@Composable
fun ApiTestScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    dogViewModel: DogViewModel = viewModel()
) {
    val giveHeartResult = dogViewModel.giveHeartResult.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { dogViewModel.giveHeartToDog(5) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "Give 5 Hearts to Dog")
        }

        // 결과 표시 (선택적)
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

        // 강아지 위치 업데이트 버튼 추가
        Button(
            onClick = { dogViewModel.updateDogPosition() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = "Update Dog Position to Current User")
        }

    }
}