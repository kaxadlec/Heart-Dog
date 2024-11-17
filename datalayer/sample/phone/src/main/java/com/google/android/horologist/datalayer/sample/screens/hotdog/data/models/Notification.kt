package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    @SerialName("user_id") val userId: Long,
    @SerialName("type_id") val typeId: Int,
    @SerialName("content") val content: String = "",
    @SerialName("created_at") val createdAt: String? = null
)