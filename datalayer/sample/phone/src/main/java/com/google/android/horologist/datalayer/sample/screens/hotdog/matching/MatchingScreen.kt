package com.google.android.horologist.datalayer.sample.screens.hotdog.matching

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.components.MatchingComponent
import androidx.compose.runtime.LaunchedEffect
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.LocalDogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel

@Composable
fun MatchingScreen(navController: NavHostController, userViewModel: UserViewModel = hiltViewModel()) {

    val dogViewModel = LocalDogViewModel.current
    val signInViewModel: SignInViewModel = hiltViewModel()
    val currentUser by signInViewModel.currentUser.collectAsState()
    val dogDetails = dogViewModel.dogDetails.collectAsState().value

    LaunchedEffect(dogDetails) {
        Log.d("MatchingScreen", "dogDetails 변경됨: $dogDetails, ViewModel: ${dogViewModel.hashCode()}")
    }

    LaunchedEffect(Unit) {
        currentUser?.userId?.let { userId ->
            Log.d("MatchingScreen", "시작: userId = $userId")
            dogViewModel.initUserAndSaveDogSession(userId, userViewModel)
        }
    }

    // dogDetails 상태 변화 추적
    LaunchedEffect(dogDetails) {
        Log.d("MatchingScreen", "강아지 상태 변화: $dogDetails")
    }

    if (currentUser?.matching != null) {
        Log.d("MatchingScreen", "매칭 상태: ${currentUser?.matching}")
        if (!currentUser?.matching!!) {
            MatchingComponent(navController = navController)
        } else {
            // 강아지 정보가 있는지 확인 후 네비게이션
            if (dogDetails != null) {
                Log.d("MatchingScreen", "강아지 정보 있음, 메인으로 이동")
                navController.navigate(HotDogMain)
            } else {
                Log.d("MatchingScreen", "강아지 정보 없음, 대기중")
            }
        }
    }

}