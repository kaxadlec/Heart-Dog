// PetTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.screens.feed.FeedScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.screens.call.CallScreen

sealed class PetTabScreen(val route: String) {
    object Main : PetTabScreen("pet_tab")
    object Feed : PetTabScreen("feed_screen")
    object Call : PetTabScreen("call_screen")
}

fun NavGraphBuilder.petTabNavigation(navController: NavController) {
    composable(PetTabScreen.Main.route) {
        PetTab(
            onNavigateToFeed = { navController.navigate(PetTabScreen.Feed.route) },
            onNavigateToCall = { navController.navigate(PetTabScreen.Call.route) }
        )
    }

    composable(PetTabScreen.Feed.route) {
        FeedScreen(
            onBack = { navController.popBackStack() }
        )
    }

    composable(PetTabScreen.Call.route) {
        CallScreen(
            onBack = { navController.popBackStack() }
        )
    }
}