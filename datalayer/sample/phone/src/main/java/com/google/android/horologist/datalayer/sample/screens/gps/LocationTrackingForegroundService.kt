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
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel

@AndroidEntryPoint
class LocationTrackingForegroundService : Service() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val TAG = "LocationService"
    }

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var userSessionManager: UserSessionManager

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        try {
            if (!checkLocationPermissions()) {
                Log.e(TAG, "Location permissions not granted")
                return
            }

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            val locationRequest = LocationRequest.Builder(5000)
                .setWaitForAccurateLocation(false)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult.lastLocation?.let { location ->
                        Log.d(TAG, "Lat: ${location.latitude}, Lng: ${location.longitude}, Alt: ${location.altitude}")

                        userSessionManager.getCurrentUserId()?.let { userId ->
                            CoroutineScope(Dispatchers.IO).launch {
                                // 1. 위치 업데이트
                                locationRepository.updateLocation(
                                    userId = userId,
                                    latitude = location.latitude,
                                    longitude = location.longitude,
                                    altitude = if (location.hasAltitude()) location.altitude else 0.0
                                )

                                // 2. 매칭 체크
                                locationRepository.checkMatching(userId)
                            }
                        } ?: run {
                            Log.e(TAG, "User ID is not available in session")
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
            Log.e(TAG, "Error in onCreate: ${e.message}")
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

}