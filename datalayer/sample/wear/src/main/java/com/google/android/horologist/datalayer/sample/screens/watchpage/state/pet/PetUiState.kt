package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

// PetUiState.kt
data class PetUiState(
    val dogId: Int = 0,       // 강아지 ID
    val coupleId: Int = 0,     // 커플 ID
    val level: Int = 1,       // 레벨
    val name: String = "",     // 이름
    val currentExp: Int = 0,   // 경험치
    val satiety: Int = 50       // 포만도 (0~100)
)