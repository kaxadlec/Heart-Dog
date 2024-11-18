package com.google.android.horologist.datalayer.sample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.android.gms.wearable.Wearable
import com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi.DogDataListener
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject

@HiltAndroidApp
class App : Application() {
    companion object {
        const val FCM_CHANNEL_ID = "fcm_default_channel"
    }

    @Inject
    lateinit var dogDataListener: DogDataListener

    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)

        // Firebase 초기화
        try {
            FirebaseApp.initializeApp(this)
            Log.d("Firebase", "Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e("Firebase", "Firebase initialization failed", e)
        }

        // FCM 알림 채널 생성
        createNotificationChannel()

        // DataClient Listener 등록
        Wearable.getDataClient(this).addListener(dogDataListener)
        Log.d("DogDataListener", "Listener registered in App")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                FCM_CHANNEL_ID,
                "FCM Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "FCM Message Channel"
                enableLights(true)
                enableVibration(true)
                setShowBadge(true)
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d("FCM", "Notification channel created")
        }
    }
}