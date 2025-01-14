package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdditionalInfoDay(
    uvIndex: String,
    humidity: String,
    wind: String,
    pressure: String
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
            uvIndex
        )
        CardAdditionalInfoDay(
            1f,
            Alignment.CenterEnd,
            5.dp,
            10.dp,
            "Влажность",
            humidity
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
            wind
        )
        CardAdditionalInfoDay(
            1f,
            Alignment.CenterEnd,
            5.dp,
            10.dp,
            "Давление",
            pressure
        )
    }
}