package com.example.features.common.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.annotatedString
import com.example.features.ui.theme.Cyan


@Composable
fun TextNavigateToView(title: String, subTitle: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = annotatedString(title, Cyan, subTitle),
        fontSize = 18.sp,
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    )
}