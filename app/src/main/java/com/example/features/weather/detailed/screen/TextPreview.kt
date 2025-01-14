package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextPreview(
    temp: String,
    description: String,
    maxTemp: String,
    minTemp: String,
    feelingTemp: String
) {
    Text(
        text = temp,
        fontSize = 50.sp,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
    )
    Text(
        text = description,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(start = 20.dp, bottom = 20.dp)
    )
    Text(
        text = "$maxTemp / $minTemp Ощущается как $feelingTemp",
        fontSize = 14.sp,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
    )
}