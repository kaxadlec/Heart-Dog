package com.google.android.horologist.datalayer.sample.screens.watchpage.state.user

// UserUiState.kt
data class UserUiState(
    val userId: String = "",
    val steps: Int = 0,
    val distance: Float = 0f,
    val heart: Int = 50, // 0-100 밥(하트)
    val hasPet: Boolean = false
)