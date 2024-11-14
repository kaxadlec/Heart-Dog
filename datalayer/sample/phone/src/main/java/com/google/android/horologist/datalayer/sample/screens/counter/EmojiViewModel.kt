package com.google.android.horologist.datalayer.sample.screens.counter

import android.util.Log
import androidx.annotation.MainThread
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.data.ProtoDataStoreHelper.protoDataStore
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import com.google.android.horologist.datalayer.sample.util.toProtoTimestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiViewModel @Inject constructor(
    private val phoneDataLayerAppHelper: PhoneDataLayerAppHelper,
    private val registry: WearDataLayerRegistry,
    private val notificationRepository: NotificationRepository,

) : ViewModel() {

    private var initializeCalled = false
    private val apiAvailable = MutableStateFlow(false)

    private lateinit var emojiDataStore: DataStore<EmojiProto.EmojiValue>
    private val _uiState = MutableStateFlow<EmojiScreenUiState>(EmojiScreenUiState.Idle)
    val uiState: StateFlow<EmojiScreenUiState> = _uiState



    @OptIn(ExperimentalCoroutinesApi::class)
    private val emojiState: Flow<EmojiProto.EmojiValue?> =
        apiAvailable.flatMapLatest { isAvailable ->
            if (!isAvailable) {
                flowOf(null)
            } else {
                emojiDataStore.data
            }
        }



    @MainThread
    fun initialize(signInViewModel: SignInViewModel) {
        if (initializeCalled) return
        initializeCalled = true

        viewModelScope.launch {


            _uiState.value = EmojiScreenUiState.CheckingApiAvailability

            if (!phoneDataLayerAppHelper.isAvailable()) {
                _uiState.value = EmojiScreenUiState.ApiNotAvailable
            } else {
                apiAvailable.value = true
                emojiDataStore =
                    registry.protoDataStore<EmojiProto.EmojiValue>(viewModelScope)
                _uiState.value = EmojiScreenUiState.Loading
            }

            signInViewModel.currentUser.collect { currentUser ->
                if (currentUser != null) {
                    emojiState.collect { emoji ->
                        if (emoji != null) {
                            when (_uiState.value) {
                                EmojiScreenUiState.Loading,
                                is EmojiScreenUiState.Loaded -> {
                                    _uiState.value = EmojiScreenUiState.Loaded(emoji = emoji.emoji)

                                    Log.d("viewmodel", "sendEmojiNotification1")

                                    // 서버로 이모지 알림 전송
                                    sendEmojiNotification(currentUser.userId, emoji.emoji)
                                }
                                else -> { /* noop */ }
                            }
                        }
                    }
                }
            }
        }

    }


    private suspend fun sendEmojiNotification(userId: Long?, emoji: String) {
        Log.d("viewmodel", "sendEmojiNotificationw")
        val typeId = 6 // 이모지 알림 타입 ID
        try {
            val result = notificationRepository.sendNotification(
                senderId = userId,
                typeId = typeId,
                content = emoji
            )
            if (result) {
                Log.d("EmojiViewModel", "Notification sent successfully for emoji: $emoji")
            } else {
                Log.e("EmojiViewModel", "Failed to send notification for emoji: $emoji")
            }
        } catch (e: Exception) {
            Log.e("EmojiViewModel", "Error sending emoji notification: ${e.message}")
        }
    }



    fun updateEmoji(newEmoji: String) {
        viewModelScope.launch {
            try {
                emojiDataStore.updateData {
                    it.toBuilder()
                        .setEmoji(newEmoji)
                        .setUpdated(System.currentTimeMillis().toProtoTimestamp())
                        .build()
                }
                Log.d("EmojiViewModel", "Updated emoji: $newEmoji")
            } catch (e: Exception) {
                Log.e("EmojiViewModel", "Failed to update emoji", e)
            }
        }
    }
}

sealed class EmojiScreenUiState {
    object Idle : EmojiScreenUiState()
    object Loading : EmojiScreenUiState()
    data class Loaded(val emoji: String) : EmojiScreenUiState()
    object CheckingApiAvailability : EmojiScreenUiState()
    object ApiNotAvailable : EmojiScreenUiState()
}
