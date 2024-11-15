package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import androidx.compose.runtime.compositionLocalOf

val LocalDogViewModel = compositionLocalOf<DogViewModel> {
    error("No DogViewModel provided")
}