package com.google.android.horologist.datalayer.sample.permission

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.horologist.datalayer.sample.MainActivity
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.gps.LocationTrackingForegroundService

class PermissionActivity : AppCompatActivity() {
    private val REQUEST_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash 화면에 표시할 TextView 생성
        val textView = TextView(this)
        textView.text = getString(R.string.activity_splash_screen) // 텍스트 설정
        textView.textSize = 24f
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER

        // TextView를 화면에 설정
        setContentView(textView)

        // 앱 실행 시 권한 요청
        requestPermissions()
    }

    @SuppressLint("InlinedApi")
    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
//            Manifest.permission.FOREGROUND_SERVICE_LOCATION
        )

        // 권한 요청
        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSION_CODE) {
            var allPermissionsGranted = true
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false
                    break
                }
            }

            if (allPermissionsGranted) {
                // 모든 권한 허용 시 MainScreen으로 이동
                val intent = Intent(this, MainActivity::class.java)
                startLocationService()
                startActivity(intent)
                finish()
            } else {
                // 권한 거부 시 처리
                showPermissionRationaleDialog() // 권한 허용 요청 다이얼로그 표시
            }
        }
    }

    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(this)
            .setTitle("권한 허용 필요")
            .setMessage("앱 기능을 위해 카메라 및 위치 정보 권한이 필요합니다. 설정에서 권한을 허용해주세요.")
            .setPositiveButton("설정으로 이동") { _, _ ->
                // 설정 화면으로 이동 (Intent.ACTION_APPLICATION_DETAILS_SETTINGS 사용)
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
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