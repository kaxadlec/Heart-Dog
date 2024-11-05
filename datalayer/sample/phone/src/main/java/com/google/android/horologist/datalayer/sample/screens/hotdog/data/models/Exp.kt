package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Exp(
    val level: Int,
    @SerialName("max_exp") val maxExp: Long
)
