package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState

@Composable
fun WeatherNameAPIView(state: WeatherDetailedState.Success) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "OpenWeatherMap",
            fontSize = 8.sp,
            color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
        )
    }
}