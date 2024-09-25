package com.example.features.design

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.features.model.Features

@Composable
fun DsFeatureItems(features: Features) {

    Row(
        Modifier
            .fillMaxWidth()
    ) {
        Text(text = features.title)
        Text(text = features.description)
    }
}