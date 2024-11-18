// GameTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple.CoupleGameScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.couple.CoupleGameScreenMissionResult
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.screens.single.SingleGameScreen

sealed class GameTabScreen(val route: String) {
    object Main : GameTabScreen("game_tab")
    object Single : GameTabScreen("single_games")
    object Couple : GameTabScreen("couple_games")
    object CoupleMissionResult : GameTabScreen("couple_game_mission_result")
}

fun NavGraphBuilder.gameTabNavigation(
    navController: NavController,
    sharedHeartRateViewModel: HeartRateViewModel,
    userViewModel: UserViewModel
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
            onNavigate = { maxHeartRate ->
                navController.navigate("${GameTabScreen.CoupleMissionResult.route}/$maxHeartRate")
            },
        )
    }

    composable(
        route = "${GameTabScreen.CoupleMissionResult.route}/{maxHeartRate}",
        arguments = listOf(
            navArgument("maxHeartRate") { type = NavType.FloatType } // maxHeartRate를 Float로 전달
        )
    ) { backStackEntry ->
        val maxHeartRate = backStackEntry.arguments?.getFloat("maxHeartRate") ?: 0f
        CoupleGameScreenMissionResult(
            onBack = { navController.popBackStack() },
            navController = navController,
            maxHeartRate = maxHeartRate,
            userViewModel = userViewModel
        )
    }


    composable(GameTabScreen.Single.route) {
        SingleGameScreen(
//            onBack = { navController.popBackStack() }
        )
    }

}