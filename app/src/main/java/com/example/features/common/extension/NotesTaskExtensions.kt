package com.example.features.common.extension

import androidx.compose.ui.graphics.Color
import com.example.features.ui.theme.Gray


fun taskItemBackgroundColor(isComplete: Boolean): Color =
    when (isComplete) {
        true -> Gray
        false -> Color.LightGray
    }

fun taskItemTextColor(isComplete: Boolean): Color =
    when (isComplete) {
        true -> Color.Gray
        false -> Color.Black
    }