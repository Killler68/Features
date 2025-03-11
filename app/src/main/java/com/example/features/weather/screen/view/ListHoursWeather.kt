package com.example.features.weather.screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.features.weather.model.WeatherData
import com.example.features.weather.model.WeatherState


@Composable
fun ListHoursWeather(weather: WeatherData, state: WeatherState.Success) {
    Box(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .background(Color.White)
            .height(1.dp)
    )
    LazyRow(
        Modifier.clip(RoundedCornerShape(20.dp))
    ) {
        items(weather.listWeek) { item -> HourlyWeatherItem(item, state) }
    }
}