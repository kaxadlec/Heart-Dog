// PetTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
//import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.screens.call.CallScreen

sealed class PetTabScreen(val route: String) {
    object Main : PetTabScreen("pet_tab")
    object Feed : PetTabScreen("feed_screen")
    object Call : PetTabScreen("call_screen")
}

fun NavGraphBuilder.petTabNavigation(navController: NavController, petViewModel: PetViewModel) {
    composable(PetTabScreen.Main.route) {
        PetTab(
            onNavigateToFeed = {},
            onNavigateToCall = { navController.navigate(PetTabScreen.Call.route)},
            petViewModel = petViewModel
        )
    }

//    composable(PetTabScreen.Call.route) {
//        CallScreen(
//            onBack = { navController.popBackStack() }
//        )
    }
//}