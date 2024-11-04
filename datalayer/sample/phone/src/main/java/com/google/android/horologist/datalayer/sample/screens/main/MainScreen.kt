/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.datalayer.sample.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import com.google.android.horologist.datalayer.sample.screens.AppHelperNodes
import com.google.android.horologist.datalayer.sample.screens.AppHelperNodesListener
import com.google.android.horologist.datalayer.sample.screens.Counter
import com.google.android.horologist.datalayer.sample.screens.CreateQRCode
import com.google.android.horologist.datalayer.sample.screens.HeartRate
import com.google.android.horologist.datalayer.sample.screens.InstallAppCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.InstallAppPromptDemo
import com.google.android.horologist.datalayer.sample.screens.InstallTileCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.InstallTilePromptDemo
import com.google.android.horologist.datalayer.sample.screens.Menu
import com.google.android.horologist.datalayer.sample.screens.ReEngageCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.ReEngagePromptDemo
import com.google.android.horologist.datalayer.sample.screens.SignInCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.SignInPromptDemo
import com.google.android.horologist.datalayer.sample.screens.counter.CounterScreen
import com.google.android.horologist.datalayer.sample.screens.heartrate.HeartRateScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp.InstallAppCustomPromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTileCustomPromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage.ReEngageCustomPromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin.SignInCustomPromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile.InstallTilePromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage.ReEngagePromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.inappprompts.signin.SignInPromptDemoScreen
import com.google.android.horologist.datalayer.sample.screens.menu.MenuScreen
import com.google.android.horologist.datalayer.sample.screens.nodes.NodesScreen
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerScreen

// 모바일 화면 개발
import com.google.android.horologist.datalayer.sample.screens.Splash
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.InsertQRCode
import com.google.android.horologist.datalayer.sample.screens.Login
import com.google.android.horologist.datalayer.sample.screens.Matching
import com.google.android.horologist.datalayer.sample.screens.Notification
import com.google.android.horologist.datalayer.sample.screens.UserInsert
import com.google.android.horologist.datalayer.sample.screens.UserSelect

import com.google.android.horologist.datalayer.sample.screens.hotdog.splash.SplashScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.main.HotDogMainScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.LoginScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.CreateQRCodeScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.InsertQRCodeScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.matching.MatchingScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.notification.NotificationScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.UserInsertScreen
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.UserSelectScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val userRepository = UserRepository()

    Scaffold(
        modifier = modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavHost(
                navController = navController,
                startDestination = Menu,
                modifier = modifier,
            ) {
                composable<Menu> {
                    MenuScreen(navController = navController)
                }

                // 추가한 부분

                composable<Splash> {
                    SplashScreen(navController = navController)
                }

                composable<Login> {
                    LoginScreen(navController = navController)
                }

                composable<Matching> {
                    MatchingScreen(navController = navController)
                }

                composable<CreateQRCode> {
                    CreateQRCodeScreen(navController = navController)
                }

                composable<InsertQRCode> {
                   InsertQRCodeScreen(navController = navController)
                }

                composable<HotDogMain> {
                    HotDogMainScreen(navController = navController)
                }

                composable<Notification> {
                    NotificationScreen(navController = navController)
                }

                // test

                composable<UserInsert> {
                    UserInsertScreen(userRepository = userRepository)
                }

                composable<UserSelect> {
                    UserSelectScreen(userRepository = userRepository)
                }



                // 기존 코드

                composable<AppHelperNodes> {
                    NodesScreen()
                }
                composable<AppHelperNodesListener> {
                    NodesListenerScreen()
                }
                composable<InstallAppPromptDemo> {
                    InstallAppPromptDemoScreen()
                }
                composable<ReEngagePromptDemo> {
                    ReEngagePromptDemoScreen()
                }
                composable<SignInPromptDemo> {
                    SignInPromptDemoScreen()
                }
                composable<InstallTilePromptDemo> {
                    InstallTilePromptDemoScreen()
                }
                composable<InstallAppCustomPromptDemo> {
                    InstallAppCustomPromptDemoScreen()
                }
                composable<ReEngageCustomPromptDemo> {
                    ReEngageCustomPromptDemoScreen()
                }
                composable<SignInCustomPromptDemo> {
                    SignInCustomPromptDemoScreen()
                }
                composable<InstallTileCustomPromptDemo> {
                    InstallTileCustomPromptDemoScreen()
                }
                composable<Counter> {
                    CounterScreen()
                }
                composable<HeartRate> {
                    HeartRateScreen()
                }
            }
        }
    }
}
