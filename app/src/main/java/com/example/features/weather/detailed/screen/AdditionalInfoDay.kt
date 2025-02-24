package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.weather.detailed.model.WeatherDetailedState

@Composable
fun AdditionalInfoDay(
    uvIndex: Double,
    humidity: Int,
    wind: Double,
    pressure: Int,
    state: WeatherDetailedState.Success
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        CardAdditionalInfoDay(
            0.5f,
            Alignment.CenterStart,
            10.dp,
            5.dp,
            "УФ-индекс",
            uvIndex.toInt().toString(),
            R.drawable.rain_drop,
            state
        )
        CardAdditionalInfoDay(
            1f,
            Alignment.CenterEnd,
            5.dp,
            10.dp,
            "Влажность",
            "$humidity%",
            R.drawable.humidity,
            state
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        CardAdditionalInfoDay(
            0.5f,
            Alignment.CenterStart,
            10.dp,
            5.dp,
            "Ветер",
            "$wind км/ч",
            R.drawable.wind,
            state
        )
        CardAdditionalInfoDay(
            1f,
            Alignment.CenterEnd,
            5.dp,
            10.dp,
            "Давление",
            "$pressure мбар",
            R.drawable.barometer,
            state
        )
    }
}