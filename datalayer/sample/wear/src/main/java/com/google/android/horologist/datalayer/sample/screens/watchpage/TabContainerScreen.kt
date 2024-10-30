package com.google.android.horologist.datalayer.sample.screens.watchpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.res.painterResource
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.CoupleTab
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.GameTab
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.HomeTab
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.PetTab
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.SettingsTab
import androidx.navigation.compose.rememberNavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.coupleTabNavigation

@Composable
fun TabContainerScreen() {
    val pagerState = rememberPagerState(
        initialPage = 1,  // MainScreen을 초기 페이지로 설정
        pageCount = { 5 }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 고정 배경 이미지
        Image(
            painter = painterResource(id = R.drawable.background_image_2),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )
        // 페이지별 탭
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> PetTab()
                1 -> HomeTab()
                2 -> {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "couple_tab"
                    ) {
                        coupleTabNavigation(navController)
                    }
                }
                3 -> GameTab()
                4 -> SettingsTab()
            }
        }

        // 간단한 페이지 인디케이터
        // 페이지 인디케이터 - 메인 탭 화면들에서만 표시
        if (pagerState.currentPage in 0..4) {  // 메인 탭 화면들에서만 표시
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(5) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    color = if (pagerState.currentPage == index)
                                        Color(0xFFFFFFFF)  // 흰색
                                    else
                                        Color(0x4DFFFFFF),  // 30% 투명도의 흰색
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}
