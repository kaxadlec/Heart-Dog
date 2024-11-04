package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,         // 이메일 (필수)
    val code: String = "",     // 기본값 설정
    val matching: Boolean = false, // 기본값 설정
    val token: String = ""     // 기본값 설정
)
