package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

// PetUiState.kt
data class PetUiState(
    val dogId: String = "1", // 강아지 ID
    val name: String = "몽실이",  // 이름
    val level: Int = 1, // 레벨
    val current_exp: Int = 0,   // 경험치
    val satiety: Int = 0,  // 포만도 (0~100)
    val position: String = ""
)