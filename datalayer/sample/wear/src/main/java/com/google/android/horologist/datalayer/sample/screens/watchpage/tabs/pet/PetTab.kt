// PetTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.pet

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
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pets
import androidx.compose.ui.platform.LocalConfiguration
import com.google.android.horologist.datalayer.sample.screens.watchpage.core.common.ui.CircleIconButton

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

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val largeSpacing = screenWidth * largeSpacingRatio
    val smallSpacing = screenWidth * smallSpacingRatio

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
            // 상태 텍스트
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(smallSpacing)
            ) {
                Text(text = "하트: ${userState.heart}", color = Color.Black)
                Text(text = "현재 포만도: ${petState.satiety}", color = Color.Black)
            }

            // 버튼들을 가로로 배치
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleIconButton(
                    text = "하트 먹이기",
                    onClick = {
                        if (userState.heart > 0) {
                            userViewModel.updateHeart(userState.heart - 1)
                            petViewModel.updateSatiety(5)
                        }
                    },
                    icon = Icons.Default.Favorite,  // 하트 아이콘
                    enabled = userState.heart > 0,  // 하트가 0이면 버튼 비활성화
                    backgroundColor = if (userState.heart > 0) Color(0xFFC56013) else Color.Gray
                )

                CircleIconButton(
                    text = "부르기",
                    onClick = onNavigateToCall,
                    icon = Icons.Default.Pets  // 반려동물 아이콘
                )
            }
        }
    }
}