// PetTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

sealed class PetTabScreen(val route: String) {
    object Main : PetTabScreen("pet_tab")
}


fun NavGraphBuilder.petTabNavigation(navController: NavController, petViewModel: PetViewModel, userViewModel: UserViewModel) {
    composable(PetTabScreen.Main.route) {
        PetTab(
            onNavigateToFeed = {},
            onNavigateToCall = {},
            petViewModel = petViewModel,
            userViewModel = userViewModel
        )
    }
}