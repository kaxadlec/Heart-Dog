package com.google.android.horologist.datalayer.sample.screens.steps

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.google.android.horologist.datalayer.sample.screens.steps.data.StepsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class StepCountService : Service() {

    @Inject
    lateinit var stepsRepository: StepsRepository

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        initializeRepository()
        startForegroundService()
        startStepCounting()
    }

    private fun initializeRepository() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sharedPreferences = getSharedPreferences("step_prefs", Context.MODE_PRIVATE)
        stepsRepository = StepsRepository(sensorManager, sharedPreferences)
    }

    private fun startForegroundService() {
        val channelId = "step_count_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Step Count Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Step Count Service")
            .setContentText("Tracking steps in the background")
            .build()

        startForeground(1, notification)
    }

    private fun startStepCounting() {
        serviceScope.launch {
            stepsRepository.stepsFlow().collect { stepDifference ->
                // 걸음 수 차이를 서버로 전송하도록 Worker 실행
                enqueueStepCountWorker(stepDifference)
            }
        }
    }

    private fun enqueueStepCountWorker(stepDifference: Int) {
        Log.d("StepCountService", "Enqueuing StepCountWorker with step difference: $stepDifference")
        val workRequest = OneTimeWorkRequestBuilder<StepCountWorker>()
            .setInputData(workDataOf("step_difference" to stepDifference))
            .build()
        WorkManager.getInstance(this).enqueueUniqueWork(
            "StepCountWorker",
            ExistingWorkPolicy.APPEND,
            workRequest
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
