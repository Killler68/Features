package com.example.features.common.extension

import androidx.compose.ui.graphics.Color


fun taskItemBackgroundColor(isComplete: Boolean, color: Color): Color =
    when (isComplete) {
        true -> Color.LightGray
        false -> color
    }

fun taskItemTextColor(isComplete: Boolean): Color =
    when (isComplete) {
        true -> Color.Gray
        false -> Color.Black
    }