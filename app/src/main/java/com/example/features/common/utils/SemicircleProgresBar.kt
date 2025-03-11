package com.example.features.common.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun SemiCircularProgress(progress: Float, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 200.dp, height = 115.dp)) {
        val strokeWidth = 10f
        val width = size.width
        val radius = width / 2 - strokeWidth / 2
        val centerX = width / 1.25f
        val centerY = size.height

        val startX = centerX - radius * 1.2f
        val endX = centerX + radius * 1.2f
        val progressEndX = startX + (endX - startX) * progress

        drawArc(
            color = Color.White,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        drawArc(
            color = Color.Yellow,
            startAngle = 180f,
            sweepAngle = 180f * progress,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        drawLine(
            color = Color.White,
            start = Offset(startX, centerY),
            end = Offset(endX, centerY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        drawLine(
            color = Color.Yellow,
            start = Offset(startX, centerY),
            end = Offset(progressEndX, centerY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}