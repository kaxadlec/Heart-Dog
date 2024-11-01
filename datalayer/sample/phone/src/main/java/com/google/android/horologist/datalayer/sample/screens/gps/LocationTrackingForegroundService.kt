package com.google.android.horologist.datalayer.sample.screens.gps

import android.Manifest
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat.startForeground
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.horologist.datalayer.sample.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocationTrackingForegroundService : Service() {

    @Inject
    lateinit var locationTrackingRepository: LocationTrackingRepository

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    // 서비스로 본인 위치 찾기
//    fun getLocation(): LocationTracking {
//        return locationTrackingRepository.findLocation()
//    }

    @RequiresPermission(allOf = [
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.FOREGROUND_SERVICE_LOCATION
    ])
    override fun onCreate() {
        super.onCreate()
        try {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            val locationRequest = LocationRequest.Builder(5000)
                .setWaitForAccurateLocation(false)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult.locations.forEach { location ->
                        Log.d("LocationService 1", "Lat: ${location.latitude}, Lng: ${location.longitude}")

                        if (locationTrackingRepository != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                locationTrackingRepository.saveLocation(
                                    location.latitude,
                                    location.longitude
                                )
                            }
                        } else {
                            Log.d("Location", "null locationTrackingRepository")
                        }

                    }
                }
            }
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            } catch (e: Exception) {
                Log.d("asdf error", "${e.message}")
            }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            Log.w("LocationService", "Received null intent in onStartCommand")
            return START_NOT_STICKY // 필요에 따라 다른 반환 값을 사용할 수 있습니다.
        }

        val notification = NotificationCompat.Builder(this, "location_channel")
            .setContentTitle("GPS Location Service")
            .setContentText("Running in background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        startForeground(1, notification)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}