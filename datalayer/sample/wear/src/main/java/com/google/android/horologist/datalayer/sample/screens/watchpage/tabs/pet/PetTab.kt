// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.wear.compose.material.MaterialTheme
import com.google.android.horologist.datalayer.sample.data.preferences.strategy.TimeRestrictionType
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.ExperienceArcs
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.DogDataSender
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.CallerDataSender


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PetTab(
    modifier: Modifier = Modifier,
    onNavigateToFeed: () -> Unit,
    onNavigateToCall: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel(),
    petViewModel: PetViewModel = hiltViewModel(),
    largeSpacingRatio: Float = 0.08f,  // 큰 간격 비율
    smallSpacingRatio: Float = 0.02f   // 작은 간격 비율
) {
    val userState by userViewModel.uiState.collectAsStateWithLifecycle()
    val petState by petViewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()  // 코루틴 스코프 생성
    val context = LocalContext.current  // 현재 컨텍스트 가져오기

    // 화면 너비에 따라 간격을 조정
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val largeSpacing = screenWidth * largeSpacingRatio
    val smallSpacing = screenWidth * smallSpacingRatio

    // 경험치 계산 로직 추가
    val requiredExpForLevel = petViewModel.getRequiredExpForLevel(petState.level)
    val expProgress = petState.currentExp / requiredExpForLevel.toFloat()

    // 테스트를 위한 1분 모드 설정
    // 하루 모드로 하려면 주석처리하면됨
    LaunchedEffect(Unit) {
        petViewModel.setTimeRestrictionType(TimeRestrictionType.MINUTE)
//        println("1분 밥 주기 제한 모드 설정 완료")
    }

    // 상태 변화 로그
    LaunchedEffect(userState.hasPet) {
        Log.d("PetTab", "hasPet 상태:${userState.hasPet} / " + "satiety 상태:${petState.satiety}")
    }

    LaunchedEffect(userState.heart) {
        Log.d("PetTab", "Heart value updated: ${userState.heart}")
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // ExperienceArcs 추가
        ExperienceArcs(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            leftProgress = petState.satiety / 100f,  // 포만도
            rightProgress = expProgress  // 경험치
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(largeSpacing)
        ) {
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
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onBackground
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
                        text = "밥주기",
                        // 하트가 0보다 크고 포만도가 100보다 작을 때만 클릭 가능
                        onClick = {
                            if (userState.hasPet && userState.heart > 0 && petState.satiety < 100) {
                                scope.launch {
                                    // DataLayer API를 통해 폰으로 데이터 전송
                                    DogDataSender.sendFeedRequestToPhone(
                                        context = context,
                                        heartAmount = 5
                                    )
                                    // 로컬 상태 업데이트
                                    userViewModel.updateHeart(userState.heart - 5)
                                    petViewModel.updateSatiety(5)
                                }
                            }
                        },
                        icon = Icons.Default.Favorite,  // 하트 아이콘
                        enabled = userState.hasPet && userState.heart > 0 && petState.satiety < 100 && petViewModel.todayFeedingCount.value < 10, // 버튼 활성화 조건
                        baseColor = if (userState.hasPet &&
                            userState.heart > 0 &&
                            petViewModel.todayFeedingCount.value < 10
                        ) Color(0xFFFF9A4D) else Color(0xFF90A4AE)
                    )

                    CircleIconButton(
                        text = "부르기",
                        onClick = {
                            Log.d("PetTab", "부르기 버튼 클릭됨") // 버튼 클릭 로그
                            userViewModel.updateHasPet(true)
                            CallerDataSender.sendCallRequestToPhone(context)
                            Log.d("PetTab", "부르기 버튼 클릭 후 hasPet 상태: ${userViewModel.uiState.value.hasPet}") // 버튼 클릭 로그
                        },
                        icon = Icons.Default.Pets,  // 반려 동물 아이콘
                        enabled = !userState.hasPet, // 이미 반려 동물이 있으면 버튼 비활성화
                        baseColor = if (!userState.hasPet) Color(0xFFFF9A4D) else Color(0xFF90A4AE)// 버튼 색상 조건
                    )
                }

            }
        }
    }
}