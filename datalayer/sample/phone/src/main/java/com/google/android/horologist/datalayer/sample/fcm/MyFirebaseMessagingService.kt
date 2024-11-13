package com.google.android.horologist.datalayer.sample.fcm

import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.android.horologist.datalayer.sample.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.android.horologist.datalayer.sample.App

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM", "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FCM", "Data Payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d("FCM", "Notification Title: ${it.title}")
            Log.d("FCM", "Notification Body: ${it.body}")
            showNotification(it.title, it.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Refreshed token: $token")
    }

    private fun showNotification(title: String?, messageBody: String?) {
        val notificationBuilder = NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                App.FCM_CHANNEL_ID,
                "FCM Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "FCM Message Channel"
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }
}