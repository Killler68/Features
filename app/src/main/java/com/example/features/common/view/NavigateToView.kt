package com.example.features.common.view

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.annotatedString
import com.example.features.ui.theme.Cyan


@Composable
fun NavigateToView(title: String, subTitle: String, onClick: () -> Unit) {
    Text(
        text = annotatedString(title, Cyan, subTitle),
        fontSize = 18.sp,
        modifier = Modifier.clickable(onClick = onClick)
    )
}