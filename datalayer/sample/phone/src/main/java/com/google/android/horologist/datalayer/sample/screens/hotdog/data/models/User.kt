package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName ("user_id") val userId: Long? = null,
    val email: String,         // 이메일 (필수)
    val code: String = "",     // 기본값 설정
    val matching: Boolean = false, // 기본값 설정
    val users_id_fkey: String = "",
    val fcm_token: String = ""
)
