package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import androidx.compose.runtime.compositionLocalOf
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel

val LocalDogViewModel = compositionLocalOf<DogViewModel> {
    error("No DogViewModel provided")
}