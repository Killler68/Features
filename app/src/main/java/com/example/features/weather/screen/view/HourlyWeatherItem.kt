package com.example.features.weather.screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.model.WeatherState
import com.example.features.weather.model.WeatherWeek


@Composable
fun HourlyWeatherItem(hourWeather: WeatherWeek, state: WeatherState.Success) {
    val temperature = "${hourWeather.temp.toInt()}°"

    Column(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                weatherColorExtension(
                    state.weatherWeek.first().partDay,
                    ColorCategory.CARD
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = hourWeather.day.dateFormatHours(),
            fontSize = 12.sp,
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
        )
        Image(
            painter = painterResource(hourWeather.icon.imageWeatherExtension(hourWeather.day)),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
        )
        Text(
            text = temperature,
            fontSize = 12.sp,
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
        )
    }
}