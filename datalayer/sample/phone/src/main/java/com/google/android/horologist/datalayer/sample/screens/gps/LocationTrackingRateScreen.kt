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

package com.google.android.horologist.datalayer.sample.screens.gps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.horologist.datalayer.sample.R

@Composable
fun LocationTrackingScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationTrackingScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state == LocationTrackingScreenUiState.Idle) {
        SideEffect {
            viewModel.initialize()
        }
    }

    LocationTrackingScreen(
        state = state,
        onClick = { viewModel.updateCurrentLocation() },
        modifier = modifier,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationTrackingScreen(
    state: LocationTrackingScreenUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    // Permissions state
    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    // Handle permission request
    LaunchedEffect(Unit) {
        locationPermissionsState.launchMultiplePermissionRequest()
    }

    // Check if permissions are granted
    val permissionsGranted = locationPermissionsState.allPermissionsGranted

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (!permissionsGranted) {
            Text(
                text = stringResource(R.string.permission_required_message),
                modifier = modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        }

        when (state) {
            LocationTrackingScreenUiState.Idle,
            LocationTrackingScreenUiState.CheckingApiAvailability,
            LocationTrackingScreenUiState.Loading,
            -> {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                )
            }

            is LocationTrackingScreenUiState.Loaded -> {
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
                            text = stringResource(
                                R.string.app_helper_location_tracking_message,
                                state.locationTracking.lat, state.locationTracking.lng
                            ),
                        )
                        Button (onClick = onClick) {
                            Icon(imageVector = Icons.Default.Update, contentDescription = "update location")
                        }
                    }
                }
            }

            LocationTrackingScreenUiState.ApiNotAvailable -> {
                Text(
                    text = stringResource(R.string.wearable_message_api_unavailable),
                    modifier.fillMaxWidth(),
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }

            else -> {}
        }
    }
}
