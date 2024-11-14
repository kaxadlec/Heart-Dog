package com.google.android.horologist.datalayer.sample.screens.hotdog.matching

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.LogoHeader
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.components.CameraPreview
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.LocalDogViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionWrapper(content: @Composable () -> Unit) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    // 권한 상태에 따라 UI 표시
    when (cameraPermissionState.status) {
        is PermissionStatus.Granted -> {
            // 권한이 허용되면 content()를 표시
            content()
        }
        is PermissionStatus.Denied -> {
            // 권한이 거부된 경우 권한 요청
            LaunchedEffect(Unit) {
                cameraPermissionState.launchPermissionRequest()
            }
        }
    }
}

@Composable
fun InsertQRCodeScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val dogViewModel = LocalDogViewModel.current

    val signInViewModel: SignInViewModel = hiltViewModel()
    val currentUser by signInViewModel.currentUser.collectAsState()

    if (currentUser == null) {
        // 로딩 UI
        LoadingScreen()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LogoHeader(navController = navController)

            CameraPermissionWrapper {
                CameraPreview(
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp),
                    onQRCodeScanned = { scannedCode ->
                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                val result = userViewModel.insertScannedCode(currentUser?.userId!!, scannedCode)
                                if (result.success && result.matched) {

                                    currentUser?.userId?.let { userId ->
                                        // 매칭 성공시 강아지 정보 가져오기
                                        dogViewModel.initUserAndSaveDogSession(userId, userViewModel)
                                    }

                                    navController.navigate(HotDogMain) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                )
            }
        }
    }
}