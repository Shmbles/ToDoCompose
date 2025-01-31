package com.azrael.to_docompose.data.model

import androidx.compose.ui.graphics.Color
import com.azrael.to_docompose.ui.theme.HighPriorityColor
import com.azrael.to_docompose.ui.theme.LowPriorityColor
import com.azrael.to_docompose.ui.theme.MediumPriorityColor
import com.azrael.to_docompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}