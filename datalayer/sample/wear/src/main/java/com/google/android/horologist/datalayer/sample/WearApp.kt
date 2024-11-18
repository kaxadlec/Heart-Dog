package com.google.android.horologist.datalayer.sample


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.WearNavigator
import androidx.wear.compose.navigation.composable
import androidx.wear.protolayout.LayoutElementBuilders.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.datalayer.sample.screens.MainScreen
import com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerScreen
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
import com.google.android.horologist.datalayer.sample.screens.watchpage.TabContainerScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

const val BODY_SENSORS_PERMISSION = Manifest.permission.BODY_SENSORS // 걸음 수 접근 권한
const val ACTIVITY_RECOGNITION_PERMISSION = Manifest.permission.ACTIVITY_RECOGNITION // 활동 인식 권한
const val ACCESS_FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION // 위치 정보 접근 권한
const val VIBRATE_PERMISSION = Manifest.permission.VIBRATE  // 진동 권한


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    petViewModel: PetViewModel,
    userViewModel: UserViewModel
) {

    // 현재 context 가져오기
    val context = LocalContext.current

    // 권한 상태를 추적하는 PermissionState 객체 생성
    val bodySensorsPermissionState = rememberPermissionState(BODY_SENSORS_PERMISSION)
    val activityRecognitionPermissionState = rememberPermissionState(ACTIVITY_RECOGNITION_PERMISSION)
    val locationPermissionState = rememberPermissionState(ACCESS_FINE_LOCATION_PERMISSION)
    val vibratePermissionState = rememberPermissionState(VIBRATE_PERMISSION)

    // 권한 요청 진행 중인지 여부를 추적
    var isRequestingPermissions by remember { mutableStateOf(false) }
    // 권한 거부 여부를 추적
    var hasPermissionDenied by remember { mutableStateOf(false) }

    // 모든 권한이 승인되었는지 확인
    val allPermissionsGranted = bodySensorsPermissionState.status.isGranted &&
            activityRecognitionPermissionState.status.isGranted &&
            locationPermissionState.status.isGranted &&
            vibratePermissionState.status.isGranted

    // 초기 권한 요청 시작
    LaunchedEffect(Unit) {
        if (!allPermissionsGranted && !hasPermissionDenied) {
            isRequestingPermissions = true
            if (!bodySensorsPermissionState.status.isGranted) {
                bodySensorsPermissionState.launchPermissionRequest()
            }
        }
    }

    // 권한 상태 변경 감지 및 다음 권한 요청
    LaunchedEffect(
        bodySensorsPermissionState.status,
        activityRecognitionPermissionState.status,
        locationPermissionState.status,
        vibratePermissionState.status
    ) {
        // 권한이 하나라도 거부되었는지 확인
        hasPermissionDenied = (!bodySensorsPermissionState.status.isGranted &&
                bodySensorsPermissionState.status.shouldShowRationale) ||
                (!activityRecognitionPermissionState.status.isGranted &&
                        activityRecognitionPermissionState.status.shouldShowRationale) ||
                (!locationPermissionState.status.isGranted &&
                        locationPermissionState.status.shouldShowRationale) ||
                (!vibratePermissionState.status.isGranted &&
                        vibratePermissionState.status.shouldShowRationale)

        // 권한이 거부되지 않은 경우에만 순차적으로 권한 요청
        if (isRequestingPermissions && !hasPermissionDenied) {
            when {
                !bodySensorsPermissionState.status.isGranted -> {
                    bodySensorsPermissionState.launchPermissionRequest()
                }
                !activityRecognitionPermissionState.status.isGranted -> {
                    activityRecognitionPermissionState.launchPermissionRequest()
                }
                !locationPermissionState.status.isGranted -> {
                    locationPermissionState.launchPermissionRequest()
                }
                !vibratePermissionState.status.isGranted -> {
                    vibratePermissionState.launchPermissionRequest()
                }
                else -> {
                    isRequestingPermissions = false
                }
            }
        }
    }

    // 모든 권한이 승인되지 않은 경우
    if (!allPermissionsGranted) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isRequestingPermissions && hasPermissionDenied) {
                // 권한이 거부된 경우 메시지
                Text(
                    text = "Heart Dog 앱 사용을 위한\n모든 권한 설정이 필요합니다",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "설정에서 모든 권한을\n허용해주세요",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9A4D)),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .widthIn(min = 120.dp)
                        .heightIn(min = 40.dp)
                ) {
                    Text(
                        text = "설정으로 이동",
                        color = Color.White,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            } else if (!isRequestingPermissions && !hasPermissionDenied) {
                // 초기 권한 요청 시작 전 메시지
                Text(
                    text = "Heart Dog 앱 사용을 위한\n권한 설정이 필요합니다",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Button(
                    onClick = { isRequestingPermissions = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9A4D)),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .widthIn(min = 120.dp)
                        .heightIn(min = 40.dp)
                ) {
                    Text(
                        text = "권한 설정하기",
                        color = Color.White,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
            // 권한 요청 중일 때는 아무것도 표시하지 않음
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
//                startDestination = Screen.MainScreen.route,
                startDestination = Screen.TabContainerScreen.route,
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
//                composable(route = Screen.HeartRateScreen.route) {
//                    Scaffold(
//                        modifier = Modifier.fillMaxSize(),
//                        timeText = { TimeText() }
//                    ) {
//                        val viewModel: HeartRateViewModel = hiltViewModel()
//                        val enabled by viewModel.enabled.collectAsState()
////
//                        val hr by viewModel.hr.collectAsState(initial = 0.0)
//                        val availability by viewModel.availability.collectAsState()
//                        val uiState by viewModel.uiState.collectAsState()
//
//                        if (uiState == UiState.Supported) {
//                            HeartRateScreen(
//                                hr = hr,
//                                availability = availability,
//                                enabled = enabled,
//                                onButtonClick = { viewModel.toggleEnabled() },
//                                permissionState = bodySensorsPermissionState
//                            )
//                        } else if (uiState == UiState.NotSupported) {
//                            NotSupportedScreen()
//                        }
//                    }
//                }
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

                // watchPage
                composable(route = Screen.TabContainerScreen.route) {
                    val columnState = rememberResponsiveColumnState(first = ItemType.Unspecified, last = ItemType.Unspecified)

                    ScreenScaffold(scrollState = columnState) {
                        TabContainerScreen(
                            sharedPetViewModel = petViewModel,
                            sharedUserViewModel = userViewModel,
//                        columnState = columnState
                        )
                    }
                }
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