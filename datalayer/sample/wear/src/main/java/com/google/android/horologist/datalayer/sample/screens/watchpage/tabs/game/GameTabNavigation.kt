// GameTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple.CoupleGameScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.single.SingleGameScreen

sealed class GameTabScreen(val route: String) {
    object Main : GameTabScreen("game_tab")
    object Single : GameTabScreen("single_games")
    object Couple : GameTabScreen("couple_games")
}

fun NavGraphBuilder.gameTabNavigation(navController: NavController) {
    composable(GameTabScreen.Main.route) {
        GameTab(
            onNavigateToSingle = { navController.navigate(GameTabScreen.Single.route) },
            onNavigateToCouple = { navController.navigate(GameTabScreen.Couple.route) }
        )
    }

    composable(GameTabScreen.Single.route) {
        SingleGameScreen(
            onBack = { navController.popBackStack() }
        )
    }

    composable(GameTabScreen.Couple.route) {
        CoupleGameScreen(
            onBack = { navController.popBackStack() }
        )
    }
}