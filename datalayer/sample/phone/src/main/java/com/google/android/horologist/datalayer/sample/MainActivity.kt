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
package com.google.android.horologist.datalayer.sample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.horologist.datalayer.sample.screens.gps.LocationTrackingForegroundService
import com.google.android.horologist.datalayer.sample.screens.main.MainScreen
import com.google.android.horologist.datalayer.sample.ui.theme.HorologistTheme
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import android.util.Log
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.NotificationViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModelFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userRepository = UserRepository()
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(userRepository)
    }
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermission()
        }

        // 사용자 ID 설정 (예시로 17L 사용)
        userViewModel.setUserId(18L)

        // FCM 토큰 가져오기 및 업데이트
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("FCM", "Failed to get FCM token", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("FCM", "Token: $token")

            // userViewModel의 userId를 observing하여 토큰 업데이트
            lifecycleScope.launch {
                userViewModel.userId.collect { userId ->
                    if (userId != null) {
                        notificationViewModel.updateFcmToken(userId, token)
                    }
                }
            }
        }

        setContent {
            HorologistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen(
                        userViewModel = userViewModel,
                        notificationViewModel = notificationViewModel,
                        onStartLocationService = { startLocationService() }
                    )
                }
            }
        }
    }

    // 알림 권한 요청
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,  // out 제거
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("FCM", "알림 권한 승인됨")
                } else {
                    Log.d("FCM", "알림 권한 거부됨")
                }
            }
        }
    }

    private fun startLocationService() {
        val intent = Intent(this, LocationTrackingForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "location_channel", // 채널 ID
                "Location Service Channel", // 채널 이름
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)

            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

}