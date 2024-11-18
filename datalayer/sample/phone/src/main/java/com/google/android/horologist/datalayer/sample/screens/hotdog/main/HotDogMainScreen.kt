package com.google.android.horologist.datalayer.sample.screens.hotdog.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi.HeartDataSender
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.TopContentBox
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.components.BottomContentRow
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils.ContentType
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.NotificationViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HotDogMainScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()

) {
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

    // 사용자 전체 정보를 관찰
    val userFullInfo by userViewModel.userFullInfo.collectAsState()
    // context 가져오기
    val context = LocalContext.current

    // 사용자 ID가 설정되었을 때 fetchUserFullInfo 호출
    LaunchedEffect(userId) {
        if (userId != null) {
            userViewModel.fetchUserFullInfo(userId)
        } else {
        }
    }

    // 사용자 정보를 로그로 출력 및 Watch로 heart 값 전송
    LaunchedEffect(userFullInfo) {
        userFullInfo?.let { info ->
            info.state_info?.let { state ->
                // heart 값을 Watch로 전송
                state.heart?.let { heart ->
                    HeartDataSender.sendHeartToWatch(context = context, heart = heart)
                }
            } ?: Log.d("UserFullInfo", "상태 정보 없음")

            info.couple_info?.let { couple ->
                Log.d(
                    "UserFullInfo",
                    "커플 정보: coupleId=${couple.couple_id}, host=${couple.host}, guest=${couple.guest}, " +
                            "hours=${couple.hours}, code=${couple.code}"
                )
            } ?: Log.d("UserFullInfo", "커플 정보 없음")
        } ?: Log.d("UserFullInfo", "사용자 정보를 불러오지 못했습니다.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopContentBox()
        BottomContentRow(
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
