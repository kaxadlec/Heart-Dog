// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pets
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.CircleIconButton
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.runtime.rememberCoroutineScope
import com.google.android.horologist.datalayer.sample.data.preferences.strategy.TimeRestrictionType
import kotlinx.coroutines.launch



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PetTab(
    modifier: Modifier = Modifier,
    onNavigateToFeed: () -> Unit,
    onNavigateToCall: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel(),
    petViewModel: PetViewModel,
    largeSpacingRatio: Float = 0.08f,  // 큰 간격 비율
    smallSpacingRatio: Float = 0.02f   // 작은 간격 비율
) {
    val userState by userViewModel.uiState.collectAsStateWithLifecycle()
    val petState by petViewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()  // 코루틴 스코프 생성

    // 화면 너비에 따라 간격을 조정
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val largeSpacing = screenWidth * largeSpacingRatio
    val smallSpacing = screenWidth * smallSpacingRatio

    // 테스트를 위한 1분 모드 설정
    // 하루 모드로 하려면 주석처리하면됨
    LaunchedEffect(Unit) {
        petViewModel.setTimeRestrictionType(TimeRestrictionType.MINUTE)
        println("1분 제한 모드 설정 완료")
    }

    // 상태 변화 로그 추가
    LaunchedEffect(userState.hasPet) {
        println("PetTab - hasPet 상태: ${userState.hasPet}")
    }

    // 상태 변화 로그
    LaunchedEffect(petState.satiety) {
        println("PetTab - Satiety Changed: ${petState.satiety}")
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(largeSpacing)
        ) {
//            // 테스트용 초기화 버튼
//            Button(
//                onClick = {
//                    scope.launch {
//                        petViewModel.resetFeedingCount()
//                        println("급여 데이터 초기화 완료")
//                    }
//                },
//                modifier = Modifier
//                   .size(40.dp)  // 작은 크기의 정사각형 버튼
//                   .padding(top = 8.dp, end = 8.dp)  // 약간의 여백
//            ) {
//                Text("리셋", fontSize = 12.sp)
//            }
            // 상태 텍스트
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(smallSpacing)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // redheart 이미지
                    Image(
                        painter = painterResource(id = R.drawable.redheart),
                        contentDescription = "Heart Icon",
                        modifier = Modifier.size(24.dp) // 원하는 크기로 조정
                    )

//                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = " ${userState.heart}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
//                Text(text = "현재 포만도 ${petState.satiety} / 100", color = Color.Black)
                }

                // 버튼들을 가로로 배치
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 먹이주기 버튼
                    CircleIconButton(
                        text = "하트 먹이기",
                        // 하트가 0보다 크고 포만도가 100보다 작을 때만 클릭 가능
                        onClick = {
                            if (userState.hasPet && userState.heart > 0 && petState.satiety < 100) {
                                scope.launch {
                                    if (petViewModel.tryFeed()) {
                                        userViewModel.updateHeart(userState.heart - 1)
                                        petViewModel.updateSatiety(5)
                                        petViewModel.checkFeedingStatus()
                                    }
                                }
                            }
                        },
                        icon = Icons.Default.Favorite,  // 하트 아이콘
                        enabled = userState.hasPet && userState.heart > 0 && petState.satiety < 100 && petViewModel.todayFeedingCount.value < 10, // 버튼 활성화 조건
                        backgroundColor = if (userState.hasPet &&
                            userState.heart > 0 &&
                            petViewModel.todayFeedingCount.value < 10) Color(0xFFD66F24) else Color.Gray
                    )

                    CircleIconButton(
                    text = "부르기",
                    onClick = {
                        println("부르기 버튼 클릭됨") // 버튼 클릭 로그
                        userViewModel.updateHasPet(true)
                        println("부르기 버튼 클릭 후 hasPet 상태: ${userViewModel.uiState.value.hasPet}") // 상태 업데이트 확인
                    },
                    icon = Icons.Default.Pets,  // 반려 동물 아이콘
                    enabled = !userState.hasPet, // 이미 반려 동물이 있으면 버튼 비활성화
                    backgroundColor = if (!userState.hasPet) Color(0xFFD66F24) else Color.Gray  // 버튼 색상 조건
                    )
                }

            }
        }
    }
}