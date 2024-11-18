package com.google.android.horologist.datalayer.sample.screens.hotdog.main.components

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.asImageBitmap
import java.util.Calendar
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.hotdog.datalayerapi.sendDogDataToWatch
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.LocalDogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.LocalSignInViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest

@Composable
fun TopContentBox() {
    val context = LocalContext.current
    val dogViewModel = LocalDogViewModel.current
    val signInViewModel = LocalSignInViewModel.current

    val dogDetails by dogViewModel.dogDetails.collectAsState()
    val currentUser by signInViewModel.currentUser.collectAsState()

    // 시간에 따른 배경 변경을 위한 체크
    val isNightTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) in 19..23 || Calendar.getInstance().get(Calendar.HOUR_OF_DAY) in 0..6

    // GIF를 위한 ImageLoader 설정
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.49f),
        contentAlignment = Alignment.Center
    ) {
        // 배경 이미지
        val backgroundBitmap = remember(isNightTime) {
            context.resources.openRawResource(
                if (isNightTime) R.drawable.night else R.drawable.day
            ).use {
                BitmapFactory.decodeStream(it)
            }?.asImageBitmap()
        }

        backgroundBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }

        dogDetails?.let { dog ->
            if (currentUser?.userId?.toInt() == dog.position) {
                val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                val isSleepingTime = currentHour in 23..23 || currentHour in 0..4

                val imageRes = if (isSleepingTime) {
                    R.drawable.sleepdog2
                } else {
                    when (dog.level) {
                        1 -> when {
                            dog.satiety < 25 -> R.drawable.onelevel1
                            dog.satiety < 50 -> R.drawable.twolevel1
                            dog.satiety < 75 -> R.drawable.threelevel1
                            else -> R.drawable.fourlevel1
                        }
                        2 -> when {
                            dog.satiety < 25 -> R.drawable.onelevel2
                            dog.satiety < 50 -> R.drawable.twolevel2
                            dog.satiety < 75 -> R.drawable.threelevel2
                            else -> R.drawable.fourlevel2
                        }
                        else -> when {
                            dog.satiety < 25 -> R.drawable.onelevel3
                            dog.satiety < 50 -> R.drawable.twolevel3
                            dog.satiety < 75 -> R.drawable.threelevel3
                            else -> R.drawable.fourlevel3
                        }
                    }
                }

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageRes)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    imageLoader = imageLoader,
                    modifier = Modifier.fillMaxSize(0.8f),
                    contentScale = ContentScale.Fit
                )
            } else {
                val houseRes = when (dog.level) {
                    1 -> R.drawable.doghouselevel1
                    2 -> R.drawable.doghouselevel2
                    else -> R.drawable.doghouselevel3
                }

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(houseRes)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    imageLoader = imageLoader,
                    modifier = Modifier.fillMaxSize(0.8f),
                    contentScale = ContentScale.Fit
                )
            }

            LaunchedEffect(dog) {
                try {
                    val hasDog = currentUser?.userId?.toInt() == dog.position
                    sendDogDataToWatch(context, dog, hasDog)
                } catch (e: Exception) {
                    Log.e("TopContentBox", "Error sending data to watch", e)
                }
            }
        }
    }
}