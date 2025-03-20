package com.example.features.features.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.ui.theme.LightGray

@Composable
fun DrawerImagePreview() {
    Image(
        painter = painterResource(R.drawable.profile),
        contentDescription = "profile",
        colorFilter = ColorFilter.tint(LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .size(180.dp),
        alignment = Alignment.Center
    )
}