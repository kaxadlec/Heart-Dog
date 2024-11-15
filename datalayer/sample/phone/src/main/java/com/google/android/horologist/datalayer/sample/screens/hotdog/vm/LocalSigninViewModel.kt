package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import androidx.compose.runtime.compositionLocalOf

val LocalSignInViewModel = compositionLocalOf<SignInViewModel> {
    error("No SignInViewModel provided")
}