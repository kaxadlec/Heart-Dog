package com.google.android.horologist.datalayer.sample.screens.watchpage

import android.util.Log
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
import androidx.compose.foundation.Image
import kotlinx.coroutines.delay
import java.util.Calendar
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
    sharedPetViewModel: PetViewModel = hiltViewModel(),
    sharedUserViewModel: UserViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState(
        initialPage = 1,  // 시작 페이지 설정
        pageCount = { 5 }  // 페이지 수 설정
    )
//    val sharedPetViewModel: PetViewModel = hiltViewModel()
//    val sharedUserViewModel: UserViewModel = hiltViewModel()
    val heartRateViewModel: HeartRateViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    var showTextOverlay by remember { mutableStateOf(false) }
    val userState by sharedUserViewModel.uiState.collectAsStateWithLifecycle()
    val petState by sharedPetViewModel.uiState.collectAsStateWithLifecycle()
    Log.d("TabContainerScreen", "Current PetState: $petState") // 상태 로그 추가

    // 각 탭의 현재 라우트 상태를 저장
    val currentHour =
        remember { mutableStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    val backgroundRes = if (currentHour.value in 19..23 || currentHour.value in 0..6) {
        R.drawable.night // 밤 배경 이미지 ID
    } else {
        R.drawable.background_image // 기본 배경 이미지 ID
    }

    val currentPetRoute = remember { mutableStateOf(PetTabScreen.Main.route) }
    val currentHomeRoute = remember { mutableStateOf(HomeTabScreen.Main.route) }
    val currentCoupleRoute = remember { mutableStateOf(CoupleTabScreen.Main.route) }
    val currentGameRoute = remember { mutableStateOf(GameTabScreen.Main.route) }
    val currentSettingsRoute = remember { mutableStateOf(SettingsTabScreen.Main.route) }

    // TODO:  여기서 FCM 알림을 구독하고 있다가 알림이 오면 userState의 상태를 바꿔준다.
    // TODO: 아래 4줄은 예시임
//    LaunchedEffect(Unit) {
//        delay(5000)
//        sharedUserViewModel.updateRecipientEmoji("❤");
//    }



    WatchPageTheme {
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
        LaunchedEffect(
            userState.eating,
            userState.working,
            userState.commuting,
            userState.commutingRecipient,
            userState.workingRecipient,
            userState.eatingRecipient,
            userState.emoji
        ) {
            if (userState.emoji != null) {
                showTextOverlay = true;
            }

            if (userState.eating || userState.working || userState.commuting || userState.commutingRecipient || userState.workingRecipient || userState.eatingRecipient) {
                showTextOverlay = true
            }
        }


        //  페이지 변경 시 로그 출력
        LaunchedEffect(pagerState.currentPage) {
            println("Current Page: ${pagerState.currentPage}")
            println(
                "Current Route: ${
                    when (pagerState.currentPage) {
                        0 -> currentPetRoute.value
                        1 -> currentHomeRoute.value
                        2 -> currentCoupleRoute.value
                        3 -> currentGameRoute.value
                        4 -> currentSettingsRoute.value
                        else -> "unknown"
                    }
                }"
            )
            println("Is Main Screen: $isMainScreen")
            println("showTextOverlay: $showTextOverlay")
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
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
                when {
                    userState.eating -> sharedUserViewModel.updateEatingStatus(false)
                    userState.working -> sharedUserViewModel.updateWorkingStatus(false)
                    userState.commuting -> sharedUserViewModel.updateCommutingStatus(false)
                    userState.eatingRecipient -> sharedUserViewModel.updateRecipientEatingStatus(
                        false
                    )

                    userState.workingRecipient -> sharedUserViewModel.updateRecipientWorkingStatus(
                        false
                    )

                    userState.commutingRecipient -> sharedUserViewModel.updateRecipientCommutingStatus(
                        false
                    )

                    userState.emoji != null -> sharedUserViewModel.updateRecipientEmoji(
                        userState.emoji
                    )
                }
                showTextOverlay = false
                coroutineScope.launch { pagerState.scrollToPage(1) }
            },
            onConfirmAction = {
                // 확인 버튼 클릭 시 수행할 동작 추가
            }
        )
    }
}
