package com.google.android.horologist.datalayer.sample.screens.motiondetector


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
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

class MotionDetectorService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null

    private val accelerometerValues = FloatArray(3)
    private val gyroscopeValues = FloatArray(3)

    private fun sendMotionDetectedBroadcast(action: String) {
        val intent = Intent("com.google.android.horologist.datalayer.MOTION_DETECTED")
        intent.putExtra("action", action)
        sendBroadcast(intent)
        // Motion detected 시 진동 추가
        vibrateOnMotionDetected()
    }


    private val typingGestureDetector = TypingGestureDetector {
        Log.d("MotionDetectorService", "Typing detected.")
        // 타이핑 감지 시 동작 추가 가능
        sendMotionDetectedBroadcast("Typing gesture detected")
    }

    private val eatingGestureDetector = EatingGestureDetector {
        Log.d("MotionDetectorService", "Eating detected.")
        // 밥 먹기 감지 시 동작 추가 가능
        sendMotionDetectedBroadcast("Eating gesture detected")
    }

    override fun onCreate() {
        super.onCreate()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (accelerometer == null) {
            Log.e("MotionDetectorService", "Accelerometer not available.")
        }
        if (gyroscope == null) {
            Log.e("MotionDetectorService", "Gyroscope not available.")
        }

        // 센서 리스너 등록
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

        startForegroundService()
    }

    private fun startForegroundService() {
        val channelId = "motion_detection_channel"
        val channel = NotificationChannel(
            channelId,
            "Motion Detection",
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("움직임 감지")
            .setContentText("움직임 알림알림알림")
            .setSmallIcon(com.google.android.horologist.datalayer.sample.R.drawable.hotdog)
            .build()

        startForeground(1, notification)
    }

    private fun vibrateOnMotionDetected() {
        val vibrator = getSystemService(Vibrator::class.java)
        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    System.arraycopy(it.values, 0, accelerometerValues, 0, it.values.size)
                }
                Sensor.TYPE_GYROSCOPE -> {
                    System.arraycopy(it.values, 0, gyroscopeValues, 0, it.values.size)
                }
            }

            // 가속도계와 자이로스코프 값이 모두 있을 때만 제스처 감지
            if (accelerometerValues.isNotEmpty() && gyroscopeValues.isNotEmpty()) {
                typingGestureDetector.detectGesture(accelerometerValues, gyroscopeValues)
                eatingGestureDetector.detectGesture(accelerometerValues, gyroscopeValues)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}