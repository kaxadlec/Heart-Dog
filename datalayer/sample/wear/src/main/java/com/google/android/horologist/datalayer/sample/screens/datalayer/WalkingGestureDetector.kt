package com.google.android.horologist.datalayer.sample.screens.datalayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class WalkingGestureDetector(
    private val context: Context,
    private val onWorkArrivalDetected: () -> Unit, // 출근 시 호출되는 콜백
    private val walkingThreshold: Float = 1.2f,
    private val minWalkingDuration: Long = 30_000L
) {
    private var lastMovementTime = 0L
    private var walkingStartTime = 0L
    private var isWalking = false
    private var lastAcceleration = 0f
    private var initialLocation: Location? = null

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest: LocationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, 10_000L
    ).setMinUpdateIntervalMillis(5_000L)
        .build()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val newLocation = locationResult.lastLocation
            if (newLocation != null && initialLocation != null) {
                val distance = initialLocation!!.distanceTo(newLocation)
                if (distance > 100) { // 100미터 이상 이동했는지 확인
                    onWorkArrivalDetected() // 출근으로 간주
                }
            }
            stopListeningForLocationUpdates() // 위치 업데이트 중지
        }
    }

    @SuppressLint("MissingPermission") // 위치 권한 확인 필요
    private fun startListeningForLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
            } catch (e: SecurityException) {
                e.printStackTrace() // SecurityException 처리
            }
        }
    }

    private val handler = Handler(Looper.getMainLooper())

    fun detectWalkingGesture(values: FloatArray) {
        val xAxisAcceleration = values[0]
        val yAxisAcceleration = values[1]
        val zAxisAcceleration = values[2]

        // 총 가속도 벡터 크기 계산
        val totalAcceleration = Math.sqrt(
            (xAxisAcceleration * xAxisAcceleration +
                    yAxisAcceleration * yAxisAcceleration +
                    zAxisAcceleration * zAxisAcceleration).toDouble()
        ).toFloat()

        val currentTime = System.currentTimeMillis()
        val accelerationChange = Math.abs(totalAcceleration - lastAcceleration)

        // 걷기 동작 감지
        if (accelerationChange in walkingThreshold..2.5f) {
            if (!isWalking) {
                walkingStartTime = currentTime
                isWalking = true
            }
            lastMovementTime = currentTime
        } else if (currentTime - lastMovementTime > 1000) {
            resetGesture()
        }

        // 걷기 시작 후 30초 경과 시 위치 정보 가져오기 시작
        if (isWalking && currentTime - walkingStartTime >= minWalkingDuration) {
            // 위치 권한 확인 후 lastLocation 호출
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                try {
                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        if (location != null) {
                            initialLocation = location // 초기 위치 설정
                            handler.postDelayed({
                                startListeningForLocationUpdates() // 3분 뒤 위치 업데이트 시작
                            }, 180_000L) // 3분 (180,000ms) 대기
                        }
                    }
                } catch (e: SecurityException) {
                    e.printStackTrace() // SecurityException 처리
                }
            }
            resetGesture()
        }

        lastAcceleration = totalAcceleration
    }

    fun stopListeningForLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun resetGesture() {
        walkingStartTime = 0L
        lastMovementTime = 0L
        isWalking = false
        lastAcceleration = 0f
    }
}
