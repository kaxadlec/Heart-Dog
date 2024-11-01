// HomeTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel

sealed class HomeTabScreen(val route: String) {
    object Main : HomeTabScreen("home_tab")
}

fun NavGraphBuilder.homeTabNavigation(
    navController: NavController,
    petViewModel: PetViewModel
) {
    composable(HomeTabScreen.Main.route) {
        HomeTab(
            petViewModel = petViewModel
        )
    }
}