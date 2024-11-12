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
}