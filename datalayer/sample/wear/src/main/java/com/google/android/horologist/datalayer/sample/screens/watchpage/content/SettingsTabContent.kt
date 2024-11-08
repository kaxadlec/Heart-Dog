package com.google.android.horologist.datalayer.sample.screens.watchpage.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.SettingsTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.settingsTabNavigation

@Composable
fun SettingsTabContent(
    currentSettingsRoute: MutableState<String>
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            currentSettingsRoute.value = entry.destination.route ?: ""
        }
    }
    NavHost(
        navController = navController,
        startDestination = SettingsTabScreen.Main.route
    ) {
        settingsTabNavigation(navController)
    }
}
