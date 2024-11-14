// HomeTab.kt
package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.google.android.horologist.datalayer.sample.R
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet.PetViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserViewModel
import com.google.android.horologist.datalayer.sample.screens.watchpage.components.ExperienceArcs
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.decode.GifDecoder
import coil.ImageLoader
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.wear.compose.material.MaterialTheme
import coil.size.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import java.util.Calendar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.*
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.wear.compose.material.Text


@Composable
fun HomeTab(
    modifier: Modifier = Modifier,
    petViewModel: PetViewModel,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val petState by petViewModel.uiState.collectAsStateWithLifecycle()
    val userState by userViewModel.uiState.collectAsStateWithLifecycle()

    // 현재 레벨에 필요한 경험치를 가져옴
    val requiredExpForLevel = petViewModel.getRequiredExpForLevel(petState.level)
    // expProgress를 현재 경험치 대비 필요 경험치의 비율로 계산
    val expProgress = petState.current_exp / requiredExpForLevel.toFloat()

    HomeTabContent(
        satietyProgress = petState.satiety / 100f,
        satiety = petState.satiety,
        expProgress = expProgress,
        name = petState.name,
        level = petState.level,
        hasPet = userState.hasPet
    )
}

@Composable
private fun HomeTabContent(
    satietyProgress: Float,
    expProgress: Float,
    name: String,
    level: Int,
    satiety: Int,
    hasPet: Boolean,
    modifier: Modifier = Modifier,
) {
    // 화면 크기 가져오기
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    // 비율로 offset과 폰트 크기 및 이미지 크기 조정
    val offsetYCenter = screenHeight * 0.10f
    val offsetYTopRatio = 0.18f
    val offsetYTop = screenHeight * offsetYTopRatio
    val fontSize = (screenWidth * 0.10f).value.sp
    val imageSize = screenWidth * 0.95f

    var visible by remember { mutableStateOf(true) }

    // 일정 시간 후 showText를 false로 설정하여 텍스트를 서서히 사라지게 함
    LaunchedEffect(Unit) {
        delay(2000) // 텍스트가 사라지기 전 3초 동안 유지
        visible = false
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // 경험치 반원
        ExperienceArcs(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            leftProgress = satietyProgress,
            rightProgress = expProgress
        )

        // 캐릭터 이미지
        PetImage(
            hasPet = hasPet,
            satiety = satiety,
            imageSize = imageSize,
            level= level,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = offsetYCenter)
        )

        // 레벨 텍스트
        if (hasPet) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = offsetYTop)
            ) {
                AnimatedVisibility(
                    visible = visible,
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 1000) // 페이드아웃 시간 (1초)
                    ),
                ) {
                    Text(
                        text = "$name LV.$level",
                        fontSize = fontSize,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@Composable
private fun PetImage(
    hasPet: Boolean,
    modifier: Modifier = Modifier,
    satiety: Int,
    level: Int,
    imageSize: Dp
) {
    val context = LocalContext.current
    val imageLoader = rememberImageLoader(context)

    // 현재 시간을 가져오기
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val isSleepingTime = currentHour in 23..23 || currentHour in 0..4

    Box(modifier = modifier) {
        if (hasPet) {
            // 잠자는 시간일 경우, sleepdog 이미지를 사용
            val imageRes = if (isSleepingTime) {
                R.drawable.sleepdog2
            } else {
                // 레벨과 포만도(satiety)에 따른 캐릭터 이미지 선택
                when (level) {
                    1 -> {
                        when {
                            satiety < 25 -> R.drawable.onelevel1
                            satiety < 50 -> R.drawable.twolevel1
                            satiety < 75 -> R.drawable.threelevel1
                            else -> R.drawable.fourlevel1
                        }
                    }
                    2 -> {
                        when {
                            satiety < 25 -> R.drawable.onelevel2
                            satiety < 50 -> R.drawable.twolevel2
                            satiety < 75 -> R.drawable.threelevel2
                            else -> R.drawable.fourlevel2
                        }
                    }
                    else -> {
                        when {
                            satiety < 25 -> R.drawable.onelevel3
                            satiety < 50 -> R.drawable.twolevel3
                            satiety < 75 -> R.drawable.threelevel3
                            else -> R.drawable.fourlevel3
                        }
                    }
                }
            }

            // 선택된 이미지 리소스 표시
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(imageRes)
                        .size(imageSize.value.toInt(), imageSize.value.toInt())
                        .build(),
                    imageLoader = imageLoader
                ),
                contentDescription = "Character",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(imageSize)
                    .align(Alignment.Center)
            )
        } else {
            // 강아지가 없는 경우 레벨에 따른 집 이미지 선택
            val houseRes = when (level) {
                1 -> R.drawable.doghouselevel1
                2 -> R.drawable.doghouselevel2
                else -> R.drawable.doghouselevel3
            }

            Image(
                painter = painterResource(id = houseRes),
                contentDescription = "Doghouse",
                modifier = Modifier
                    .size(imageSize)
                    .align(Alignment.Center)
            )
        }
    }
}
@Composable
fun TextWithGradientFade(
    text: String,
    fontSize: TextUnit,
    alpha: Float,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = Color.White.copy(alpha = alpha), // 텍스트 전체 투명도 조정
        modifier = modifier.drawWithContent {
            val brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.White),
                startY = size.height / 2,
                endY = size.height
            )
            drawContent()
            drawRect(brush = brush, alpha = alpha) // 그라데이션 효과로 아래쪽이 서서히 사라짐
        }
    )
}

@Composable
private fun PetLevelText(
    name: String,
    level: Int,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$name LV.$level",
        fontSize = fontSize,
        color = MaterialTheme.colors.onBackground,
        modifier = modifier
    )
}

@Composable
private fun rememberImageLoader(context: Context): ImageLoader {
    return remember(context) {
        ImageLoader.Builder(context)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
    }
}