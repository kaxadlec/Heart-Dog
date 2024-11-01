// GameTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple.CoupleGameScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple.CoupleGameScreenMission
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.single.SingleGameScreen

sealed class GameTabScreen(val route: String) {
    object Main : GameTabScreen("game_tab")
    object Single : GameTabScreen("single_games")
    object Couple : GameTabScreen("couple_games")
    object CoupleMission : GameTabScreen("couple_game_mission")
}

fun NavGraphBuilder.gameTabNavigation(
    navController: NavController
) {
    composable(GameTabScreen.Main.route) {
        GameTab(
            onNavigateToSingle = { navController.navigate(GameTabScreen.Single.route) },
            onNavigateToCouple = { navController.navigate(GameTabScreen.Couple.route) }
        )
    }
    composable(GameTabScreen.Couple.route) {
        CoupleGameScreen(
            onBack = { navController.popBackStack() },
            onNavigate = { navController.navigate(GameTabScreen.CoupleMission.route) }
        )
    }
    composable(GameTabScreen.CoupleMission.route) {
        CoupleGameScreenMission(
            onBack = {navController.popBackStack()}
        )
    }

    composable(GameTabScreen.Single.route) {
        SingleGameScreen(
            onBack = { navController.popBackStack() }
        )
    }
}