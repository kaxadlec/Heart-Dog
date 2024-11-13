package com.google.android.horologist.datalayer.sample.screens.datalayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiServiceGrpcKt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiViewModel @Inject constructor(
    private val emojiService: EmojiServiceGrpcKt.EmojiServiceCoroutineStub
) : ViewModel() {

    private val _emojiState = MutableStateFlow<String?>(null)
    val emojiState: StateFlow<String?> = _emojiState

    /**
     * 폰으로 이모지 전송
     */
    fun sendEmojiToPhone(emoji: String) {
        viewModelScope.launch {
            try {
                emojiService.setEmoji(
                    EmojiProto.EmojiRequest.newBuilder().setEmoji(emoji).build()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

