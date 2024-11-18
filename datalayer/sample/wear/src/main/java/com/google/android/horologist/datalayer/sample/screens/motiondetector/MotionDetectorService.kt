package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MotionDetectorService : Service(), SensorEventListener {

    @Inject
    lateinit var emojiService: EmojiServiceGrpcKt.EmojiServiceCoroutineStub

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null

    private val accelerometerValues = FloatArray(3)
    private val gyroscopeValues = FloatArray(3)
    private val serviceScope = CoroutineScope(Dispatchers.IO)

    companion object {
        private const val CHANNEL_ID = "motion_detection_channel"
        private const val NOTIFICATION_ID = 1002
    }

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
        initializeSensors()
    }

    private fun startForegroundService() {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.hotdog)
            .build()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, "Motion Detection", NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun vibrateOnMotionDetected() {
        val vibrator = getSystemService(Vibrator::class.java)
        vibrator?.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    private fun sendEmojiToPhone(emoji: String) {
        serviceScope.launch {
            try {
                emojiService.setEmoji(
                    EmojiProto.EmojiRequest.newBuilder().setEmoji(emoji).build()
                )
                Log.d("MotionDetectorService", "Emoji sent: $emoji")
            } catch (e: Exception) {
                Log.e("MotionDetectorService", "Failed to send emoji", e)
            }
        }
    }

    private fun sendDataLayerMessage(action: String) {
        val dataClient = Wearable.getDataClient(this)
        val dataMapRequest = PutDataMapRequest.create("/motion_detected").apply {
            dataMap.putString("action", action)
        }.asPutDataRequest()

        serviceScope.launch {
            dataClient.putDataItem(dataMapRequest)
        }
    }

    private fun sendMotionDetectedNotification(action: String) {
        vibrateOnMotionDetected()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val sendIntent = Intent(this, MotionDetectorService::class.java).apply {
            putExtra("action", action)
        }
        val pendingIntent = PendingIntent.getService(
            this, 0, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationText = when (action) {
            "Typing gesture detected" -> "타이핑 중이신가요? 상대방에게 알려드릴까요?"
            "Eating gesture detected" -> "식사 중이신가요? 상대방에게 알려드릴까요?"
            else -> "움직임이 감지되었습니다."
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.hotdog)
            .setContentText(notificationText)
            .addAction(0, "보내기", pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private val typingGestureDetector = TypingGestureDetector {
        Log.d("MotionDetectorService", "Typing detected.")
        sendMotionDetectedNotification("Typing gesture detected")
    }

    private val eatingGestureDetector = EatingGestureDetector {
        Log.d("MotionDetectorService", "Eating detected.")
        sendMotionDetectedNotification("Eating gesture detected")
    }

    private fun initializeSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> System.arraycopy(it.values, 0, accelerometerValues, 0, it.values.size)
                Sensor.TYPE_GYROSCOPE -> System.arraycopy(it.values, 0, gyroscopeValues, 0, it.values.size)
            }

            if (accelerometerValues.isNotEmpty() && gyroscopeValues.isNotEmpty()) {
                typingGestureDetector.detectGesture(accelerometerValues, gyroscopeValues)
                eatingGestureDetector.detectGesture(accelerometerValues, gyroscopeValues)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("action")
        action?.let {
            when (it) {
                "Typing gesture detected" -> sendEmojiToPhone("💻")
                "Eating gesture detected" -> sendEmojiToPhone("🍽️")
            }
        }
        return START_STICKY
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
