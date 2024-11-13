package com.google.android.horologist.datalayer.sample.screens.hotdog.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.TopContentBox
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.BottomContentRow
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.ContentType
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.NotificationViewModel
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HotDogMainScreen(navController: NavController, dogViewModel: DogViewModel) {
    var selectedContent by remember { mutableStateOf(ContentType.ABOUT) } // 선택된 콘텐츠 상태 관리

    val signInViewModel: SignInViewModel = hiltViewModel()
    val notificationViewModel: NotificationViewModel = hiltViewModel()

    val currentUser by signInViewModel.currentUser.collectAsState()
    val userId = currentUser?.userId

    if (userId != null) {
        updateFcmToken(notificationViewModel, userId)
    } else {
        Log.d("noti User init", "null")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopContentBox()
        BottomContentRow(
            dogViewModel = dogViewModel,
            onButtonClick = { content -> selectedContent = content }, // onButtonClick 전달
            selectedContent = selectedContent
        )
    }
    ButtonFooter(navController = navController)
}


private fun updateFcmToken(
    notificationViewModel: NotificationViewModel,
    userId: Long?
) {
    if (userId != null) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("FCM", "Failed to get FCM token", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("FCM", "Token: $token")

            // userViewModel의 userId를 observing하여 토큰 업데이트
            CoroutineScope(Dispatchers.IO).launch {

                notificationViewModel.updateFcmToken(userId, token)

            }
        }
    } else {
        Log.d("UserId", "User is null")
    }
}
