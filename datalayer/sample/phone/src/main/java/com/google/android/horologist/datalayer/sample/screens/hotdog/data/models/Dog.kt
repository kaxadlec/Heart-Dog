package com.google.android.horologist.datalayer.sample.screens.hotdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    @SerialName("dog_id") val dogId: Long,
    @SerialName("couple_id") val coupleId: Int,
    val level: Int,
    val name: String = "",
    @SerialName("current_exp") val currentExp: Long,
    val satiety: Int
)
