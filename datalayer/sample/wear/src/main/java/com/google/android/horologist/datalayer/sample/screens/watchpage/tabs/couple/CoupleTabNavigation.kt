package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens.WalkScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens.TimeTogetherScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens.EmojiScreen

sealed class CoupleTabScreen(val route: String) {
    object Main : CoupleTabScreen("couple_main")
    object Walk : CoupleTabScreen("couple_walk")
    object TimeTogether : CoupleTabScreen("couple_time")
    object Emoji : CoupleTabScreen("couple_emoji")
}

fun NavGraphBuilder.coupleTabNavigation(
    navController: NavHostController
) {
    navigation(
        startDestination = CoupleTabScreen.Main.route,
        route = "couple_tab"
    ) {
        composable(CoupleTabScreen.Main.route) {
            CoupleTab(
                onWalkClick = { navController.navigate(CoupleTabScreen.Walk.route) },
                onTimeTogetherClick = { navController.navigate(CoupleTabScreen.TimeTogether.route) },
                onEmojiClick = { navController.navigate(CoupleTabScreen.Emoji.route) }
            )
        }

        composable(CoupleTabScreen.Walk.route) {
            WalkScreen(stepCount = 4200)
        }

        composable(CoupleTabScreen.TimeTogether.route) {
            TimeTogetherScreen(timeTogether = 3)
        }

        composable(CoupleTabScreen.Emoji.route) {
            EmojiScreen()
        }
    }
}