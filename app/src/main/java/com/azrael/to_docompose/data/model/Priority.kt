package com.azrael.to_docompose.data.model

import HighPriorityColor
import LowPriorityColor
import MediumPriorityColor
import androidx.compose.ui.graphics.Color

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(Color.Transparent)
}