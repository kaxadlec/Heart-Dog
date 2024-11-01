package com.google.android.horologist.datalayer.sample.screens.watchpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.res.painterResource
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.CoupleTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.coupleTabNavigation
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.GameTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.gameTabNavigation
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home.HomeTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home.homeTabNavigation
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.PetTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.petTabNavigation
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.SettingsTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.settingsTabNavigation
import com.google.android.horologist.datalayer.sample.screens.watchpage.theme.WatchPageTheme

@Composable
fun TabContainerScreen() {
    WatchPageTheme {
        val pagerState = rememberPagerState(
            initialPage = 1,  // MainScreen을 초기 페이지로 설정
            pageCount = { 5 }
        )
        val sharedPetViewModel: PetViewModel = hiltViewModel() // 공유 뷰모델 생성

        // 각 탭의 현재 라우트 상태를 저장
        val currentPetRoute = remember { mutableStateOf(PetTabScreen.Main.route) }
        val currentHomeRoute = remember { mutableStateOf(HomeTabScreen.Main.route) }
        val currentCoupleRoute = remember { mutableStateOf(CoupleTabScreen.Main.route) }
        val currentGameRoute = remember { mutableStateOf(GameTabScreen.Main.route) }
        val currentSettingsRoute = remember { mutableStateOf(SettingsTabScreen.Main.route) }

        // 현재 화면이 메인 화면인지 확인
        val isMainScreen = when (pagerState.currentPage) {
            0 -> currentPetRoute.value == PetTabScreen.Main.route
            1 -> currentHomeRoute.value == HomeTabScreen.Main.route
            2 -> currentCoupleRoute.value == CoupleTabScreen.Main.route
            3 -> currentGameRoute.value == GameTabScreen.Main.route
            4 -> currentSettingsRoute.value == SettingsTabScreen.Main.route
            else -> false
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 고정 배경 이미지
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize()
            )
            // 페이지별 탭
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                // 메인 화면에서만 스와이프 가능
                userScrollEnabled = isMainScreen
            ) { page ->
                when (page) {
                    0 -> {
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
                            petTabNavigation(navController, sharedPetViewModel)
                        }
                    }

                    1 -> {
                        val navController = rememberNavController()
                        LaunchedEffect(navController) {
                            navController.currentBackStackEntryFlow.collect { entry ->
                                currentHomeRoute.value = entry.destination.route ?: ""
                            }
                        }
                        NavHost(
                            navController = navController,
                            startDestination = HomeTabScreen.Main.route
                        ) {
                            homeTabNavigation(navController, sharedPetViewModel)
                        }
                    }

                    2 -> {
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

                    3 -> {
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
                            gameTabNavigation(navController)  // 새로 추가할 게임 탭 내비게이션
                        }
                    }

                    4 -> {
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
                }
            }

            if (isMainScreen) {
                PageIndicator(
                    pagerState = pagerState,
                    indicatorCount = 5,
                    indicatorSizeRatio = 0.03f,
                    curveRadiusRatio = 0.6f,
                    paddingBottomRatio = 0.28f,
                    activeColor = Color(0xFFFFFFFF),
                    inactiveColor = Color(0x4DFFFFFF),
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
