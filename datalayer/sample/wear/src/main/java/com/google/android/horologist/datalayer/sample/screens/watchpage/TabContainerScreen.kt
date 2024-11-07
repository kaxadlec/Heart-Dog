package com.google.android.horologist.datalayer.sample.screens.watchpage
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.res.painterResource
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
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
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel


@Composable
fun TabContainerScreen(
) {
    WatchPageTheme {
        val pagerState = rememberPagerState(
            initialPage = 1,  // 시작 페이지 설정
            pageCount = {5}  // 페이지 수 설정
        )
//        val pagerState = rememberPagerState(
//            initialPage = 1,  // MainScreen을 초기 페이지로 설정
//            pageCount = { 5 }
//        )
        val sharedPetViewModel: PetViewModel = hiltViewModel() // 공유 뷰모델 생성
        val sharedUserViewModel: UserViewModel = hiltViewModel()
        val coroutineScope = rememberCoroutineScope()
        var showTextOverlay by remember { mutableStateOf(true) }


        val userState by sharedUserViewModel.uiState.collectAsStateWithLifecycle()

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
                            petTabNavigation( navController = navController,
                                petViewModel = sharedPetViewModel,
                                userViewModel = sharedUserViewModel)
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
                            homeTabNavigation(
                                navController = navController,
                                petViewModel = sharedPetViewModel,
                                userViewModel = sharedUserViewModel)
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
                        val heartRateViewModel: HeartRateViewModel = hiltViewModel()

                        LaunchedEffect(navController) {
                            navController.currentBackStackEntryFlow.collect { entry ->
                                currentGameRoute.value = entry.destination.route ?: ""
                            }
                        }
                        NavHost(
                            navController = navController,
                            startDestination = GameTabScreen.Main.route
                        ) {
                            gameTabNavigation(navController = navController, sharedHeartRateViewModel = heartRateViewModel  )  // 새로 추가할 게임 탭 내비게이션
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
            if (userState.isCoupleMatched) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "모바일에서 커플 매칭을 완료해주세요",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
            if (userState.eating && showTextOverlay) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "움직임을 감지했습니다. 식사 중이십니까?",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                onClick = {
                                    showTextOverlay = false
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(1)
                                    }
                                }
                            ) {
                                Text("닫기")
                            }

                            Button(
                                onClick = { /* 확인 버튼 클릭 시 동작 추가 */ }
                            ) {
                                Text("확인")
                            }
                        }
                    }
                }
            }
            if (userState.working && showTextOverlay) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "움직임을 감지했습니다. 근무 중이십니까?",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                onClick = {
                                    showTextOverlay = false
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(1)
                                    }
                                }
                            ) {
                                Text("닫기")
                            }

                            Button(
                                onClick = { /* 확인 버튼 클릭 시 동작 추가 */ }
                            ) {
                                Text("확인")
                            }
                        }
                    }
                }
            }
            if (userState.commuting && showTextOverlay) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "움직임을 감지했습니다. 출근 중이십니까?",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                onClick = {
                                    showTextOverlay = false
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(1)
                                    }
                                }
                            ) {
                                Text("닫기")
                            }

                            Button(
                                onClick = { /* 확인 버튼 클릭 시 동작 추가 */ }
                            ) {
                                Text("확인")
                            }
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
                    activeColor = Color(0xFFFFA500),
                    inactiveColor = Color(0xFFFFCC80),
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

        }
    }
}
