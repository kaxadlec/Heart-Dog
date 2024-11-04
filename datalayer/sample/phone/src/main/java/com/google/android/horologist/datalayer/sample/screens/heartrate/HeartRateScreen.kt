/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.datalayer.sample.screens.heartrate

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.horologist.datalayer.sample.R

@Composable
fun HeartRateScreen(
    modifier: Modifier = Modifier,
    viewModel: HeartRateScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state == HeartRateScreenUiState.Idle) {
        SideEffect {
            viewModel.initialize()
        }
    }

    HeartRateScreen(
        state = state,
//        onPlusClick = { viewModel.updateHeartRate() },
        modifier = modifier,
    )
}

@Composable
fun HeartRateScreen(
    state: HeartRateScreenUiState,
//    onPlusClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        when (state) {
            HeartRateScreenUiState.Idle,
            HeartRateScreenUiState.CheckingApiAvailability,
            HeartRateScreenUiState.Loading,
            -> {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                )
            }

            is HeartRateScreenUiState.Loaded -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text(text = stringResource(R.string.app_helper_heart_rate_increase_explanation))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = stringResource(R.string.app_helper_heart_rate_message, state.heartRate),
                        )
//                        Button(onClick = onPlusClick) {
//                            Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Plus 1")
//                        }
                    }
                }
            }

            HeartRateScreenUiState.ApiNotAvailable -> {
                Text(
                    text = stringResource(R.string.wearable_message_api_unavailable),
                    modifier.fillMaxWidth(),
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
