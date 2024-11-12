package com.google.android.horologist.datalayer.sample.screens.gps

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.LocationRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.annotation.RequiresApi
import android.os.Build

@AndroidEntryPoint
class LocationTrackingForegroundService : Service() {

    companion object {
        private const val NOTIFICATION_ID = 1  // 위치 추적 알림 ID
    }

    @Inject
    lateinit var locationRepository: LocationRepository

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        try {
            // 권한 체크
            if (!checkLocationPermissions()) {
                Log.e("LocationService", "Location permissions not granted")
                return
            }

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            val locationRequest = LocationRequest.Builder(5000) // 10초마다 업데이트 (테스트용)
                .setWaitForAccurateLocation(false)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult.locations.forEach { location ->
                        Log.d("LocationService",
                            "Lat: ${location.latitude}, Lng: ${location.longitude}, Alt: ${location.altitude}")

                        CoroutineScope(Dispatchers.IO).launch {
                            // 1. 위치 업데이트
                            locationRepository.updateLocation(
                                userId = getCurrentUserId(),
                                latitude = location.latitude,
                                longitude = location.longitude,
                                altitude = if (location.hasAltitude()) location.altitude else 0.0
                            )

                            // 2. 매칭 체크
                            locationRepository.checkMatching(getCurrentUserId())
                        }
                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: Exception) {
            Log.e("LocationService", "Error: ${e.message}")
        }
    }

    private fun checkLocationPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, "location_channel")
            .setContentTitle("위치 추적")
            .setContentText("위치 추적 중...")
            .setSmallIcon(R.drawable.ic_notification)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ServiceCompat.startForeground(
                this,
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun getCurrentUserId(): Long {
        // TODO: 실제 사용자 ID 가져오기 구현
        return 17L // 임시로 하드코딩
    }
}