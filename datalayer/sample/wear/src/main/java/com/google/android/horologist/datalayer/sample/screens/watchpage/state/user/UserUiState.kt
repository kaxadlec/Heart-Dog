package com.google.android.horologist.datalayer.sample.screens.watchpage.state.user

// UserUiState.kt
data class UserUiState(
    val userId: String = "",          // 사용자 ID
    val dogId: String? = null,        // 연결된 강아지 ID
    val coupleId: String? = null,     // 연결된 커플 ID
    val hasPet: Boolean = true,
    val isCoupleMatched: Boolean = true,
    val steps: Int = 0,
    val distance: Float = 0f,
    val heart: Int = 50, // 0-100 밥(하트)
    val eating: Boolean = false,
    val working: Boolean = false,
    val commuting: Boolean = false,
    val eatingRecipient: Boolean = false,
    val workingRecipient: Boolean = false,
    val commutingRecipient: Boolean = false
)