package com.google.android.horologist.datalayer.sample.screens.hotdog.matching

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.viewmodel.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.components.MatchingComponent

@Composable
fun MatchingScreen(navController: NavHostController) {

    val signInViewModel: SignInViewModel = hiltViewModel()
    val currentUser by signInViewModel.currentUser.collectAsState()

    if (currentUser?.matching != null) {
        if (!currentUser?.matching!!) {
            MatchingComponent(navController = navController)
        } else {
            navController.navigate(HotDogMain)
        }
    }

}