package com.google.android.horologist.datalayer.sample.screens.hotdog.matching.components

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.horologist.datalayer.sample.R

@Composable
fun CameraPreview(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    var isCameraReady by remember { mutableStateOf(false) } // 카메라 준비 상태를 확인하는 변수

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                cameraProvider.bindToLifecycle(
                    ctx as androidx.lifecycle.LifecycleOwner,
                    cameraSelector,
                    preview
                )

                isCameraReady = true // 카메라가 준비되면 true로 변경
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        },
        modifier = modifier.then(
            Modifier.size(300.dp)
        )
    )

    // 카메라가 준비되지 않았을 때 보여줄 임시 화면
    if (!isCameraReady) {
        Box(
            modifier = modifier
                .size(250.dp)
                .background(Color.Gray), // 임시 배경색
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.main_logo), // 임시 이미지 리소스
                contentDescription = "Camera Placeholder",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}