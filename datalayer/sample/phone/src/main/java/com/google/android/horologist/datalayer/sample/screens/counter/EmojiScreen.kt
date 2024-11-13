package com.google.android.horologist.datalayer.sample.screens.counter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EmojiScreen(
    modifier: Modifier = Modifier,
    viewModel: EmojiViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // ViewModel 초기화
    if (state == EmojiScreenUiState.Idle) {
        SideEffect {
            viewModel.initialize()
        }
    }

    // UI 화면 구성
    EmojiScreenContent(
        state = state,
        modifier = modifier
    )
}

@Composable
fun EmojiScreenContent(
    state: EmojiScreenUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            EmojiScreenUiState.Idle,
            EmojiScreenUiState.CheckingApiAvailability,
            EmojiScreenUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }

            is EmojiScreenUiState.Loaded -> {
                Text(
                    text = "받은 이모지: ${state.emoji}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }

            EmojiScreenUiState.ApiNotAvailable -> {
                Text(
                    text = "API를 사용할 수 없습니다.",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
