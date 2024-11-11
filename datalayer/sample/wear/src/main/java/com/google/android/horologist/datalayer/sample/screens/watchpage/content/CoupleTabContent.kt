package com.google.android.horologist.datalayer.sample.screens.watchpage.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.CoupleTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.coupleTabNavigation

@Composable
fun CoupleTabContent(
    currentCoupleRoute: MutableState<String>
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            currentCoupleRoute.value = entry.destination.route ?: ""
        }
    }
    NavHost(
        navController = navController,
        startDestination = CoupleTabScreen.Main.route
    ) {
        coupleTabNavigation(navController)
    }
}
