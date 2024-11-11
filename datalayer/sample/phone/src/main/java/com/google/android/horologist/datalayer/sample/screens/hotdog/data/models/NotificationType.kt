package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationType(
    @SerialName("type_id") val typeId: Int,
    val category: String
)