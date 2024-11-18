package com.google.android.horologist.datalayer.sample.grpc

import android.util.Log
import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmojiService @Inject constructor(
    private val emojiDataStore: DataStore<EmojiProto.EmojiValue>,
    private val notificationRepository: NotificationRepository,
    private val userSessionManager: UserSessionManager
) : EmojiServiceGrpcKt.EmojiServiceCoroutineImplBase() {

    private val _receivedEmoji = MutableStateFlow("초기 이모지")
    val receivedEmoji: StateFlow<String> get() = _receivedEmoji

    init {
        // DataStore에서 데이터 변경을 수신하고 StateFlow를 업데이트합니다.
        CoroutineScope(Dispatchers.IO).launch {
            emojiDataStore.data.collect { currentValue ->
                _receivedEmoji.value = currentValue.emoji
                Log.d("EmojiService", "Emoji updated: ${currentValue.emoji}")
            }
        }
    }

    override suspend fun setEmoji(request: EmojiProto.EmojiRequest): EmojiProto.EmojiValue {
        Log.d("EmojiService", "Received emoji: ${request.emoji}")

        val updatedValue = emojiDataStore.updateData { currentValue ->
            currentValue.toBuilder()
                .setEmoji(request.emoji)
                .setUpdated(System.currentTimeMillis().toProtoTimestamp())
                .build()
        }

        val userId = userSessionManager.getCurrentUserId()
        if (userId != null) {
            sendNotification(userId, request.emoji)
        } else {
            Log.e("EmojiService", "No logged-in user found")
        }

        return updatedValue
    }

    suspend fun sendNotification(userId: Long, emoji: String) {
        val typeId = 6
        try {
            val result = notificationRepository.sendNotification(
                senderId = userId,
                typeId = typeId,
                content = emoji
            )
            if (result) {
                Log.d("EmojiService", "Notification sent successfully")
            } else {
                Log.e("EmojiService", "Failed to send notification")
            }
        } catch (e: Exception) {
            Log.e("EmojiService", "Error sending notification: ${e.message}")
        }
    }

    override suspend fun getEmoji(request: com.google.protobuf.Empty): EmojiProto.EmojiValue {
        return emojiDataStore.data.firstOrNull() ?: EmojiProto.EmojiValue.getDefaultInstance()
    }
}
