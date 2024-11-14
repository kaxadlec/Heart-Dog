package com.google.android.horologist.datalayer.sample.screens.watchpage.state.pet

// PetUiState.kt
data class PetUiState(
    val level: Int = 1,       // 레벨
    val name: String = "",     // 이름
    val currentExp: Long = 0,   // 경험치
    val satiety: Int = 50,       // 포만도 (0~100)
    val position: Int = 1,    // 위치
    val maxExp: Long = 100,    // 최대 경험치
)