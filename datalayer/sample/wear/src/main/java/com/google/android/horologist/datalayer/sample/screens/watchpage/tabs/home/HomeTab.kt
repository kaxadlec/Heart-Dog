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
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.wear.compose.material.MaterialTheme
import coil.size.Size

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
    val expProgress = petState.exp / requiredExpForLevel.toFloat()

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
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 18.dp)
        )

        // 레벨 텍스트
        if (hasPet) {
            PetLevelText(
                name = name,
                level = level,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 30.dp)
            )
        }
    }
}

@Composable
private fun PetImage(
    hasPet: Boolean,
    modifier: Modifier = Modifier,
    satiety: Int,
) {
    val context = LocalContext.current
    val imageLoader = rememberImageLoader(context)

    Box(modifier = modifier) {
        if (hasPet) {
            val imageRes = when {
                satiety < 25 -> R.drawable.cryingmotion
                satiety < 50 -> R.drawable.umnaumna
                satiety < 75 -> R.drawable.pppig
                else -> R.drawable.dog_animation_1
            }

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(imageRes)
                        .size(Size(120, 120))
                        .build(),
                    imageLoader = imageLoader
                ),
                contentDescription = "Character",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else {
            Image(
                painter = painterResource(id = R.drawable.doghouse),
                contentDescription = "Doghouse",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun PetLevelText(
    name: String,
    level: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$name LV.$level",
        fontSize = 15.sp,
        color = MaterialTheme.colors.onBackground ,
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