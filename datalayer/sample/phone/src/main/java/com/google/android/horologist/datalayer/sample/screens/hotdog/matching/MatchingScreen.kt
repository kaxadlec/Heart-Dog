package com.google.android.horologist.datalayer.sample.screens.hotdog.matching

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.components.MatchingComponent

@Composable
fun MatchingScreen(navController: NavHostController) {

    MatchingComponent(navController = navController)

}