package com.example.features.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicatorsView(
    modifier: Modifier = Modifier,
    count: Int,
    currentPage: Int,
    activeColor: Color,
    inactiveColor: Color
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(
                        width = if (index == currentPage) 60.dp else 24.dp,
                        height = 12.dp
                    )
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        if (index == currentPage) activeColor else inactiveColor
                    )
            )
        }
    }
}