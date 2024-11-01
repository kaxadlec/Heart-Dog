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

@file:OptIn(ExperimentalFoundationApi::class)

package com.google.android.horologist.datalayer.sample

import android.Manifest
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.datalayer.sample.screens.MainScreen
import com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerScreen
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateScreen
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.NotSupportedScreen
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.UiState
import com.google.android.horologist.datalayer.sample.screens.info.infoScreen
import com.google.android.horologist.datalayer.sample.screens.info.navigateToInfoScreen
import com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesScreen
import com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionsScreen
import com.google.android.horologist.datalayer.sample.screens.nodesactions.navigateToNodeDetailsScreen
import com.google.android.horologist.datalayer.sample.screens.nodesactions.nodeDetailsScreen
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerScreen
import com.google.android.horologist.datalayer.sample.screens.steps.StepsScreen
import com.google.android.horologist.datalayer.sample.screens.steps.StepsViewModel
import com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreen

const val TAG = "Measure Data Sample"

const val LOCATION_PERMISSION = android.Manifest.permission.ACCESS_FINE_LOCATION
const val BODY_SENSORS_PERMISSION = Manifest.permission.BODY_SENSORS
const val ACTIVITY_RECOGNITION_PERMISSION = Manifest.permission.ACTIVITY_RECOGNITION


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberSwipeDismissableNavController(),
) {

    AppScaffold {
        SwipeDismissableNavHost(
            startDestination = Screen.MainScreen.route,
            navController = navController,
            modifier = modifier,
        ) {
            composable(
                route = Screen.MainScreen.route,
            ) {
                val columnState = rememberResponsiveColumnState(first = ItemType.Unspecified, last = ItemType.Unspecified)

                ScreenScaffold(scrollState = columnState) {
                    MainScreen(
                        navigateToRoute = navController::navigate,
                        columnState = columnState,
                    )
                }
            }
            composable(route = Screen.CounterScreen.route) {
                val columnState = rememberResponsiveColumnState(first = ItemType.Unspecified, last = ItemType.Unspecified)
                val bodySensorsPermissionState = rememberPermissionState(BODY_SENSORS_PERMISSION)

                val permissionGranted = bodySensorsPermissionState.status.isGranted

                ScreenScaffold(scrollState = columnState) {
                    if (!permissionGranted) {
                        // 권한이 부여되지 않았을 경우 권한 요청
                        LaunchedEffect(Unit) {
                            bodySensorsPermissionState.launchPermissionRequest()
                        }
                        // 권한 대기 중일 때 메시지 표시
                        Text(
                            text = "Please grant BODY_SENSORS permission to access data.",
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.error
                        )
                    } else {
                        // 권한이 부여된 경우에만 DataLayerScreen 표시
                        DataLayerScreen(columnState = columnState)
                    }
                }
            }
            composable(route = Screen.ListNodesScreen.route) {
                val columnState = rememberResponsiveColumnState(first = ItemType.Unspecified, last = ItemType.Unspecified)

                ScreenScaffold(scrollState = columnState) {
                    DataLayerNodesScreen(columnState = columnState)
                }
            }
            composable(route = Screen.AppHelperTrackingScreen.route) {
                val columnState = rememberResponsiveColumnState(first = ItemType.Unspecified, last = ItemType.Unspecified)

                ScreenScaffold(scrollState = columnState) {
                    TrackingScreen(
                        onDisplayInfoClicked = navController::navigateToInfoScreen,
                        columnState = columnState,
                    )
                }
            }
            composable(route = Screen.AppHelperNodesActionsScreen.route) {
                val columnState = rememberResponsiveColumnState(first = ItemType.Unspecified, last = ItemType.Unspecified)

                ScreenScaffold(scrollState = columnState) {
                    NodesActionsScreen(
                        onNodeClick = navController::navigateToNodeDetailsScreen,
                        columnState = columnState,
                    )
                }
            }
            composable(route = Screen.AppHelperNodesListenerScreen.route) {
                val columnState = rememberResponsiveColumnState(
                    first = ItemType.Unspecified,
                    last = ItemType.Unspecified
                )
                ScreenScaffold(scrollState = columnState) {
                    NodesListenerScreen(columnState = columnState)
                }
            }
            nodeDetailsScreen()
            infoScreen(
                onDismissClick = navController::popBackStack,
            )


            composable(route = Screen.HeartRateScreen.route) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    timeText = { TimeText() }
                ) {
                    val viewModel: HeartRateViewModel = hiltViewModel()

                    val enabled by viewModel.enabled.collectAsState()
                    val hr by viewModel.hr
                    val availability by viewModel.availability
                    val uiState by viewModel.uiState

                    if (uiState == UiState.Supported) {
                        val permissionState = rememberPermissionState(
                            permission = BODY_SENSORS_PERMISSION,
                            onPermissionResult = { granted ->
                                if (granted) viewModel.toggleEnabled()
                            }
                        )
                        HeartRateScreen(
//                            columnState = columnState,
                            hr = hr,
                            availability = availability,
                            enabled = enabled,
                            onButtonClick = { viewModel.toggleEnabled() },
                            permissionState = permissionState
                        )

                    } else if (uiState == UiState.NotSupported) {
                        NotSupportedScreen()
                    }
                }
            }

            composable(route = Screen.StepsScreen.route) {
                val viewModel: StepsViewModel = hiltViewModel()
                val columnState = rememberResponsiveColumnState(ItemType.Unspecified, ItemType.Unspecified)


                val bodySensorsPermissionState = rememberPermissionState(BODY_SENSORS_PERMISSION)
                val activityRecognitionPermissionState = rememberPermissionState(ACTIVITY_RECOGNITION_PERMISSION)

                val permissionsGranted = bodySensorsPermissionState.status.isGranted && activityRecognitionPermissionState.status.isGranted

                ScreenScaffold(scrollState = columnState) {
                    if (!permissionsGranted) {
                        // Request permissions if not granted
                        LaunchedEffect(Unit) {
                            bodySensorsPermissionState.launchPermissionRequest()
                            activityRecognitionPermissionState.launchPermissionRequest()
                        }
                        // Display message while waiting for permission grant
                        Text(
                            text = "Please grant permissions to access step count.",
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.error
                        )
                    } else {
                        // Show StepsScreen if permissions are granted
                        StepsScreen(
                            columnState = columnState,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

//@WearPreviewSmallRound
//@Composable
//fun DefaultPreview() {
//    WearApp()
//}
