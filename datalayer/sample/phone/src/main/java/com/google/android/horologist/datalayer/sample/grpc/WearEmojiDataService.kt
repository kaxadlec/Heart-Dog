package com.google.android.horologist.datalayer.sample.grpc

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import com.google.android.horologist.datalayer.sample.MainActivity
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import com.google.android.horologist.datalayer.sample.shared.EmojiValueSerializer
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WearEmojiDataService : BaseGrpcDataService<EmojiServiceGrpcKt.EmojiServiceCoroutineImplBase>() {

    companion object {
        private const val NOTIFICATION_ID = 101
        private const val CHANNEL_ID = "emoji_channel"
        private const val TAG = "WearEmojiDataService"
    }

    @Inject
    lateinit var notificationRepository: NotificationRepository

    @Inject
    lateinit var userSessionManager: UserSessionManager

    private lateinit var emojiService: EmojiService

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val dataStore: DataStore<EmojiProto.EmojiValue> by lazy {
        DataStoreFactory.create(
            serializer = EmojiValueSerializer,
            produceFile = { dataStoreFile("emoji_prefs.pb") },
            scope = serviceScope
        )
    }

    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = serviceScope
        ).apply {
            registerSerializer(EmojiValueSerializer)
        }
    }


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")
        createNotificationChannel()
        startForegroundService()

        // EmojiService 인스턴스 초기화 및 데이터 변경 사항 수신
        emojiService = buildService() as EmojiService
        observeEmojiUpdates()
    }

    private fun observeEmojiUpdates() {
        // DataStore에서 변경 사항을 수신하고 로그 출력
        serviceScope.launch {
            emojiService.receivedEmoji.collect { emoji ->
                Log.d(TAG, "Emoji updated: $emoji")
            }
        }
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Emoji Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun startForegroundService() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("이모지 서비스")
            .setContentText("이모지 알림 서비스 실행 중...")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ServiceCompat.startForeground(
                this,
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    override fun buildService(): EmojiServiceGrpcKt.EmojiServiceCoroutineImplBase {
        return EmojiService(
            dataStore,
            notificationRepository,
            userSessionManager
        )
    }
}
