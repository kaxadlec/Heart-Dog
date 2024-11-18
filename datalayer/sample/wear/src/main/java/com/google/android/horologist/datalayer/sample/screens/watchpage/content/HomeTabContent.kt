package com.google.android.horologist.datalayer.sample.screens.watchpage.content

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home.HomeTab
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home.HomeTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home.homeTabNavigation

@Composable
fun HomeTabContent(
    currentHomeRoute: MutableState<String>,
    petViewModel: PetViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            currentHomeRoute.value = entry.destination.route ?: ""
        }
    }

    val petState by petViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(petState) {
        Log.d("HomeTabContent", "Updated petState: $petState")
    }

    val userState by userViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(userState) {
        Log.d("HomeTabContent", "Updated userState: $userState")
    }


    NavHost(
        navController = navController,
        startDestination = HomeTabScreen.Main.route
    ) {
        homeTabNavigation(
            navController = navController,
            petViewModel = petViewModel,
            userViewModel = userViewModel
        )
    }
}
