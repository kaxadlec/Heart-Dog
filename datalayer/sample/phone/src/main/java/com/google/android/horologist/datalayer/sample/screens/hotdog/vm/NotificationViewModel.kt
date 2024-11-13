package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: NotificationRepository
) : ViewModel() {

    fun updateFcmToken(userId: Long, token: String) {
        viewModelScope.launch {
            repository.updateFcmToken(userId, token)
        }
    }

    fun sendDogCallNotification(senderId: Long) {
        viewModelScope.launch {
            repository.sendNotification(
                senderId = senderId,
                typeId = 1,  // type -> typeId로 변경
                content = "강아지가 호출되었습니다!"
            )
        }
    }

    fun sendStepNotification(senderId: Long) {
        viewModelScope.launch {
            repository.sendNotification(
                senderId = senderId,
                typeId = 4,
                content = "5,000걸음을 함께 걸으셨어요!"
            )
        }
    }

    fun sendEmojiNotification(senderId: Long) {
        viewModelScope.launch {
            repository.sendNotification(
                senderId = senderId,
                typeId = 6,
                content = " \uD83D\uDE03"
            )
        }
    }
}