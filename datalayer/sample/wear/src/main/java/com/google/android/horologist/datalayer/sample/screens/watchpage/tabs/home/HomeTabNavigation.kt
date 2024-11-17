// HomeTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

sealed class HomeTabScreen(val route: String) {
    object Main : HomeTabScreen("home_tab")
}

fun NavGraphBuilder.homeTabNavigation(
    navController: NavController,
    petViewModel: PetViewModel,
    userViewModel: UserViewModel
) {
    composable(HomeTabScreen.Main.route) {
        val petState = petViewModel.uiState.collectAsStateWithLifecycle().value
        val userState = userViewModel.uiState.collectAsStateWithLifecycle().value

        HomeTab(
            petState = petState,
            userState = userState
        )
    }
}