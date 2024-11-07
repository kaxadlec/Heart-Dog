package com.google.android.horologist.datalayer.sample.screens.watchpage.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.GameTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.gameTabNavigation

@Composable
fun GameTabContent(
    currentGameRoute: MutableState<String>,
    heartRateViewModel: HeartRateViewModel,
    userViewModel: UserViewModel
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            currentGameRoute.value = entry.destination.route ?: ""
        }
    }
    NavHost(
        navController = navController,
        startDestination = GameTabScreen.Main.route
    ) {
        gameTabNavigation(
            navController = navController,
            sharedHeartRateViewModel = heartRateViewModel,
            userViewModel = userViewModel
        )
    }
}
