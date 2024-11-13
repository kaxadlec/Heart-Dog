package com.google.android.horologist.datalayer.sample.grpc

import android.util.Log
import androidx.datastore.core.DataStore
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class EmojiService @Inject constructor(
    private val emojiDataStore: DataStore<EmojiProto.EmojiValue>
) : EmojiServiceGrpcKt.EmojiServiceCoroutineImplBase() {

    private val _receivedEmoji = MutableStateFlow("초기 이모지")
    val receivedEmoji: StateFlow<String> get() = _receivedEmoji

    // 이모지 설정
    override suspend fun setEmoji(request: EmojiProto.EmojiRequest): EmojiProto.EmojiValue {
        Log.d("EmojiService", "Received emoji: ${request.emoji}")

        val updatedValue = emojiDataStore.updateData { currentValue ->
            currentValue.toBuilder()
                .setEmoji(request.emoji)
                .setUpdated(System.currentTimeMillis().toProtoTimestamp())
                .build()
        }

        // StateFlow 업데이트
        _receivedEmoji.value = updatedValue.emoji
        Log.d("EmojiService", "Updated emoji value: ${_receivedEmoji.value}")
        return updatedValue
    }

    // 현재 저장된 이모지 가져오기
    override suspend fun getEmoji(request: com.google.protobuf.Empty): EmojiProto.EmojiValue {
        val currentValue = emojiDataStore.data.firstOrNull() ?: EmojiProto.EmojiValue.getDefaultInstance()
        Log.d("EmojiService", "Fetching emoji: ${currentValue.emoji}")
        return currentValue
    }
}
