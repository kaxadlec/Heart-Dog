// SettingsTabNavigation.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.screens.guide.GuideScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.screens.reset.ResetScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.screens.logout.LogoutScreen

sealed class SettingsTabScreen(val route: String) {
    object Main : SettingsTabScreen("settings_tab")
    object Guide : SettingsTabScreen("guide_screen")
    object Reset : SettingsTabScreen("reset_screen")
    object Logout : SettingsTabScreen("logout_screen")
}

fun NavGraphBuilder.settingsTabNavigation(navController: NavController) {
    composable(SettingsTabScreen.Main.route) {
        SettingsTab(
            onNavigateToGuide = { navController.navigate(SettingsTabScreen.Guide.route) },
            onNavigateToReset = { navController.navigate(SettingsTabScreen.Reset.route) },
            onNavigateToLogout = { navController.navigate(SettingsTabScreen.Logout.route) }
        )
    }

    composable(SettingsTabScreen.Guide.route) {
        GuideScreen(
            onBack = { navController.popBackStack() }
        )
    }

    composable(SettingsTabScreen.Reset.route) {
        ResetScreen(
            onBack = { navController.popBackStack() }
        )
    }

    composable(SettingsTabScreen.Logout.route) {
        LogoutScreen(
            onBack = { navController.popBackStack() }
        )
    }
}