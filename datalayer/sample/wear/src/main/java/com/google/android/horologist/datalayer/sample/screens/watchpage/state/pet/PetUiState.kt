package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

// PetUiState.kt
data class PetUiState(
    val name: String = "강아지이름",  // 이름
    val level: Int = 1, // 레벨
    val exp: Int = 0,   // 경험치
    val satiety: Int = 0  // 포만도 (0~100)
)