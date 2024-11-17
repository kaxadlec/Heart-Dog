package com.google.android.horologist.datalayer.sample.screens.motiondetector

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.Screen
import com.google.android.horologist.datalayer.sample.WearApp
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel

@Composable
fun MainActivityContent(
    navController: NavHostController = rememberNavController(),
    petViewModel: PetViewModel,
    userViewModel: UserViewModel,
) {
    val context = LocalContext.current as Activity
    val intent = context.intent
    val screen = intent.getStringExtra("screen")

    LaunchedEffect(screen) {
        if (screen == "motionDetectorScreen") {
            navController.navigate(Screen.MotionDetectorScreen.route)
        }
    }

    DisposableEffect(Unit) {
        val motionDetectedReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.getStringExtra("action")
                action?.let {
                    when (it) {
                        "Typing gesture detected" -> {
                            navController.navigate(Screen.MotionDetectorScreen.route)
                        }
                        "Eating gesture detected" -> {
                            navController.navigate(Screen.MotionDetectorScreen.route)
                        }
                    }
                }
            }
        }

        val filter = IntentFilter("com.google.android.horologist.datalayer.MOTION_DETECTED")
        context.registerReceiver(motionDetectedReceiver, filter, Context.RECEIVER_NOT_EXPORTED)

        onDispose {
            context.unregisterReceiver(motionDetectedReceiver)
        }
    }

    // Render the main app UI
    WearApp(navController = navController, petViewModel = petViewModel, userViewModel = userViewModel)
}