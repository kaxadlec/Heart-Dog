package com.google.android.horologist.datalayer.sample.screens.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.google.android.horologist.datalayer.sample.screens.steps.StepsScreenViewModel.StepsScreenUiState

@Composable
fun StepCountScreen(
    modifier: Modifier = Modifier,
    viewModel: StepsScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state == StepsScreenUiState.Idle) {
        SideEffect {
            viewModel.initialize()
        }
    }

    StepCountScreenContent(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun StepCountScreenContent(
    state: StepsScreenUiState,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        when (state) {
            StepsScreenUiState.Idle -> {
                Text(text = "Initializing...", modifier = modifier)
            }
            StepsScreenUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.width(64.dp))
            }
            is StepsScreenUiState.Loaded -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Current Step Count:")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "${state.stepCount} steps"
                        )
                    }
                }
            }
            StepsScreenUiState.ApiNotAvailable -> {
                Text(
                    text = "API not available",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }
            else -> {
                Text(text = "Unknown state", modifier = modifier)
            }
        }
    }
}

