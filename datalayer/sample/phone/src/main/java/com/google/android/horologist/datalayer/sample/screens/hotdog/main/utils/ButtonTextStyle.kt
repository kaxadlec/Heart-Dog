package com.google.android.horologist.datalayer.sample.screens.hotdog.main.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.google.android.horologist.datalayer.sample.ui.theme.textColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor50

data class ButtonTextStyle(val fontWeight: FontWeight, val color: Color)

fun buttonTextStyle(isSelected: Boolean): ButtonTextStyle {
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    val color = if (isSelected) textColor else textColor50
    return ButtonTextStyle(fontWeight, color)
}
