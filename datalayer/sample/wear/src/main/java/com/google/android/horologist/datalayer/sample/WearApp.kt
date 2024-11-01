package com.google.android.horologist.datalayer.sample


import android.Manifest
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.WearNavigator
import androidx.wear.compose.navigation.composable
import androidx.wear.protolayout.LayoutElementBuilders.Text
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
import com.google.android.horologist.datalayer.sample.screens.motiondetector.MotionDetectorScreen
import com.google.android.horologist.datalayer.sample.screens.motiondetector.MotionDetectorService
import com.google.android.horologist.datalayer.sample.screens.motiondetector.MotionDetectorViewModel
import com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesScreen
import com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionsScreen
import com.google.android.horologist.datalayer.sample.screens.nodesactions.navigateToNodeDetailsScreen
import com.google.android.horologist.datalayer.sample.screens.nodesactions.nodeDetailsScreen
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerScreen
import com.google.android.horologist.datalayer.sample.screens.steps.StepsScreen
import com.google.android.horologist.datalayer.sample.screens.steps.StepsViewModel
import com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreen


const val BODY_SENSORS_PERMISSION = Manifest.permission.BODY_SENSORS
const val ACTIVITY_RECOGNITION_PERMISSION = Manifest.permission.ACTIVITY_RECOGNITION
const val ACCESS_FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
const val VIBRATE_PERMISSION = Manifest.permission.VIBRATE


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val context = LocalContext.current

    // 각 권한 상태 관리
    val bodySensorsPermissionState = rememberPermissionState(BODY_SENSORS_PERMISSION)
    val activityRecognitionPermissionState = rememberPermissionState(ACTIVITY_RECOGNITION_PERMISSION)
    val locationPermissionState = rememberPermissionState(ACCESS_FINE_LOCATION_PERMISSION)
    val vibratePermissionState = rememberPermissionState(VIBRATE_PERMISSION)

    // 권한 상태 변경 및 로그 확인
    Log.d("WearApp", "BODY_SENSORS_PERMISSION granted: ${bodySensorsPermissionState.status.isGranted}")
    Log.d("WearApp", "ACTIVITY_RECOGNITION_PERMISSION granted: ${activityRecognitionPermissionState.status.isGranted}")
    Log.d("WearApp", "ACCESS_FINE_LOCATION_PERMISSION granted: ${locationPermissionState.status.isGranted}")
    Log.d("WearApp", "VIBRATE_PERMISSION granted: ${vibratePermissionState.status.isGranted}")


    // 모든 권한이 승인된 경우 확인
    val allPermissionsGranted = bodySensorsPermissionState.status.isGranted &&
            activityRecognitionPermissionState.status.isGranted &&
            locationPermissionState.status.isGranted

    if (!allPermissionsGranted) {
        // 개별적으로 권한 요청
        LaunchedEffect(Unit) {
            if (!bodySensorsPermissionState.status.isGranted) {
                Log.d("WearApp", "Requesting BODY_SENSORS_PERMISSION")
                bodySensorsPermissionState.launchPermissionRequest()
            } else if (!activityRecognitionPermissionState.status.isGranted) {
                Log.d("WearApp", "Requesting ACTIVITY_RECOGNITION_PERMISSION")
                activityRecognitionPermissionState.launchPermissionRequest()
            } else if (!locationPermissionState.status.isGranted) {
                Log.d("WearApp", "Requesting ACCESS_FINE_LOCATION_PERMISSION")
                locationPermissionState.launchPermissionRequest()
            } else if (!vibratePermissionState.status.isGranted) {
                Log.d("WearApp", "Requesting VIBRATE_PERMISSION")
                vibratePermissionState.launchPermissionRequest()
            }
        }

        // 권한 요청 메시지 UI 표시
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Please grant permissions to access step count, activity recognition, and location.",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error
            )
        }
    } else {
        // 모든 권한 승인 후 서비스 시작
        LaunchedEffect(Unit) {
            Log.d("WearApp", "All permissions granted. Starting MotionDetectorService.")
            val intent = Intent(context, MotionDetectorService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        navController.navigatorProvider.addNavigator(WearNavigator())


        AppScaffold {
            SwipeDismissableNavHost(
                startDestination = Screen.MainScreen.route,
                navController = navController,
                modifier = modifier,
            ) {
                composable(route = Screen.MainScreen.route) {
                    val columnState = rememberResponsiveColumnState(
                        first = ItemType.Unspecified,
                        last = ItemType.Unspecified
                    )

                    ScreenScaffold(scrollState = columnState) {
                        MainScreen(
                            navigateToRoute = navController::navigate,
                            columnState = columnState,
                        )
                    }
                }

                // App Helpers 및 기타 composable들 정의
                composable(route = Screen.AppHelperTrackingScreen.route) {
                    val columnState = rememberResponsiveColumnState()
                    ScreenScaffold(scrollState = columnState) {
                        TrackingScreen(
                            onDisplayInfoClicked = navController::navigateToInfoScreen,
                            columnState = columnState,
                        )
                    }
                }
                composable(route = Screen.AppHelperNodesActionsScreen.route) {
                    val columnState = rememberResponsiveColumnState()
                    ScreenScaffold(scrollState = columnState) {
                        NodesActionsScreen(
                            onNodeClick = navController::navigateToNodeDetailsScreen,
                            columnState = columnState,
                        )
                    }
                }
                composable(route = Screen.AppHelperNodesListenerScreen.route) {
                    val columnState = rememberResponsiveColumnState()
                    ScreenScaffold(scrollState = columnState) {
                        NodesListenerScreen(columnState = columnState)
                    }
                }
                composable(route = Screen.CounterScreen.route) {
                    val columnState = rememberResponsiveColumnState()
                    ScreenScaffold(scrollState = columnState) {
                        DataLayerScreen(columnState = columnState)
                    }
                }
                composable(route = Screen.ListNodesScreen.route) {
                    val columnState = rememberResponsiveColumnState()
                    ScreenScaffold(scrollState = columnState) {
                        DataLayerNodesScreen(columnState = columnState)
                    }
                }
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
                            HeartRateScreen(
                                hr = hr,
                                availability = availability,
                                enabled = enabled,
                                onButtonClick = { viewModel.toggleEnabled() },
                                permissionState = bodySensorsPermissionState
                            )
                        } else if (uiState == UiState.NotSupported) {
                            NotSupportedScreen()
                        }
                    }
                }
                composable(route = Screen.StepsScreen.route) {
                    val viewModel: StepsViewModel = hiltViewModel()
                    val columnState = rememberResponsiveColumnState(ItemType.Unspecified, ItemType.Unspecified)

                    ScreenScaffold(scrollState = columnState) {
                        StepsScreen(
                            columnState = columnState,
                            viewModel = viewModel
                        )
                    }
                }

                // MotionDetectorScreen을 위한 composable 추가
                composable(route = Screen.MotionDetectorScreen.route) {
                    val context = LocalContext.current
                    val viewModel: MotionDetectorViewModel = hiltViewModel()
                    MotionDetectorScreen(
                        viewModel = viewModel,
                        onSwipeUp = {
                            val serviceIntent = Intent(context, MotionDetectorService::class.java)
                            context.stopService(serviceIntent) // 서비스 종료
                            navController.popBackStack() // 현재 화면 닫기
                        }
                    )
                }

                // nodeDetailsScreen 및 infoScreen 추가
                nodeDetailsScreen()
                infoScreen(
                    onDismissClick = navController::popBackStack,
                )
            }
        }
    }
}

@Composable
fun Column(
    modifier: Modifier,
    verticalArrangement: Arrangement.HorizontalOrVertical,
    horizontalAlignment: SnapPosition.Center,
    content: @Composable () -> Text
) {
    TODO("Not yet implemented")
}