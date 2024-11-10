package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val level: Int,
    val name: String,
    @SerialName("current_exp") val currentExp: Long,
    val satiety: Int,
    @SerialName("position") val position: Int,
    @SerialName("max_exp") val maxExp: Long
)
