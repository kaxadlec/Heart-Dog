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
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi

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
fun InsertQRCodeScreen(navController: NavHostController) {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center

    ) {

        LogoHeader(navController = navController)

        // 1. 카메라 연결
        // 2. 데이터 전송

        CameraPermissionWrapper {
            CameraPreview(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
        }

    }

}