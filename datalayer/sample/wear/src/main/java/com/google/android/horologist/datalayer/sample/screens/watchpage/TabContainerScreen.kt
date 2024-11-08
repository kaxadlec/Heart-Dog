package com.google.android.horologist.datalayer.sample.screens.watchpage
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.PageIndicator
import com.google.android.horologist.datalayer.sample.screens.watchpage.content.CoupleTabContent
import com.google.android.horologist.datalayer.sample.screens.watchpage.content.GameTabContent
import com.google.android.horologist.datalayer.sample.screens.watchpage.content.HomeTabContent
import com.google.android.horologist.datalayer.sample.screens.watchpage.content.PetTabContent
import com.google.android.horologist.datalayer.sample.screens.watchpage.content.SettingsTabContent
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.CoupleTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.game.GameTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home.HomeTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet.PetTabScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.settings.SettingsTabScreen
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
        val sharedPetViewModel: PetViewModel = hiltViewModel() // 공유 뷰모델 생성
        val sharedUserViewModel: UserViewModel = hiltViewModel()
        val heartRateViewModel: HeartRateViewModel = hiltViewModel()
        val coroutineScope = rememberCoroutineScope()
        var showTextOverlay by remember { mutableStateOf(false) }
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

        // 식사, 근무, 출근 중일 때 텍스트 오버레이 표시
        LaunchedEffect(userState.eating, userState.working, userState.commuting) {
            if (userState.eating || userState.working || userState.commuting) {
                showTextOverlay = true
            }
        }

        //  페이지 변경 시 로그 출력
        LaunchedEffect(pagerState.currentPage) {
            println("Current Page: ${pagerState.currentPage}")
            println("Current Route: ${
                when (pagerState.currentPage) {
                    0 -> currentPetRoute.value
                    1 -> currentHomeRoute.value
                    2 -> currentCoupleRoute.value
                    3 -> currentGameRoute.value
                    4 -> currentSettingsRoute.value
                    else -> "unknown"
                }
            }")
            println("Is Main Screen: $isMainScreen")
            println("showTextOverlay: $showTextOverlay")
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
                userScrollEnabled = isMainScreen  // 메인 화면에서만 스와이프 가능
            ) { page ->
                when (page) {
                    0 -> PetTabContent(currentPetRoute, sharedPetViewModel, sharedUserViewModel)
                    1 -> HomeTabContent(currentHomeRoute, sharedPetViewModel, sharedUserViewModel)
                    2 -> CoupleTabContent(currentCoupleRoute)
                    3 -> GameTabContent(currentGameRoute, heartRateViewModel, sharedUserViewModel)
                    4 -> SettingsTabContent(currentSettingsRoute)
                }

            }

            if (isMainScreen && !showTextOverlay) {
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
        // UserStatusOverlay 컴포저블 호출
        UserStatusOverlay(
            userState = userState,
            showTextOverlay = showTextOverlay,
            onCloseOverlay = {
                showTextOverlay = false
                coroutineScope.launch { pagerState.scrollToPage(1) }
            },
            onConfirmAction = {
                showTextOverlay = false
                // 확인 버튼 클릭 시 수행할 동작 추가
            }
        )
    }
}
