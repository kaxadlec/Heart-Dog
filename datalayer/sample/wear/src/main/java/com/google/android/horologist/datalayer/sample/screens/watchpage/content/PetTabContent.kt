package com.google.android.horologist.datalayer.sample.screens.watchpage.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.PetTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.petTabNavigation

@Composable
fun PetTabContent(
    currentPetRoute: MutableState<String>,
    petViewModel: PetViewModel,
    userViewModel: UserViewModel
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            currentPetRoute.value = entry.destination.route ?: ""
        }
    }
    NavHost(
        navController = navController,
        startDestination = PetTabScreen.Main.route
    ) {
        petTabNavigation(
            navController = navController,
            petViewModel = petViewModel,
            userViewModel = userViewModel
        )
    }
}
