package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.common.extension.weatherVisibilityExtension
import com.example.features.weather.detailed.model.WeatherDetailedState

@Composable
fun AdditionalInfoDayView(state: WeatherDetailedState.Success) {
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
            "Видимость",
            weatherVisibilityExtension(state.detailedDay.visibility),
            R.drawable.visibility,
            state
        )
        CardAdditionalInfoDay(
            1f,
            Alignment.CenterEnd,
            5.dp,
            10.dp,
            "Влажность",
            "${state.detailedDay.humidity}%",
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
            "${state.detailedDay.windSpeed} м/с",
            R.drawable.wind,
            state
        )
        CardAdditionalInfoDay(
            1f,
            Alignment.CenterEnd,
            5.dp,
            10.dp,
            "Давление",
            "${state.detailedDay.pressure} мбар",
            R.drawable.barometer,
            state
        )
    }
}