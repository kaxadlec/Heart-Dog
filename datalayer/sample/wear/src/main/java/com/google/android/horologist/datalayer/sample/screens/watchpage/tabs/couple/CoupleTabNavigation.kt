package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple

import androidx.compose.runtime.MutableState
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
    composable(CoupleTabScreen.Main.route) {
        CoupleTab(
            onWalkClick = { navController.navigate(CoupleTabScreen.Walk.route) },
            onTimeTogetherClick = { navController.navigate(CoupleTabScreen.TimeTogether.route) },
            onEmojiClick = { navController.navigate(CoupleTabScreen.Emoji.route) }
        )
    }

    composable(CoupleTabScreen.Walk.route) {
        WalkScreen(
//            onBack = { navController.popBackStack() }
        )
    }

    composable(CoupleTabScreen.TimeTogether.route) {
        TimeTogetherScreen(
//            onBack = { navController.popBackStack() }
        )
    }

    composable(CoupleTabScreen.Emoji.route) {
        EmojiScreen(
//            onBack = { navController.popBackStack() }
        )
    }

}