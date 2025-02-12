package com.example.features.weather.detailed.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.ui.theme.WeatherHoursBackground
import com.example.features.weather.detailed.model.WeatherDetailedState

@Composable
fun HoursInfoDay(
    description: String,
    state: WeatherDetailedState.Success
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(WeatherHoursBackground)
    ) {
        Text(
            text = description.getRawNameWeatherExtension(),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            color = Color.White
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
                .size(width = 1.dp, height = 1.dp)
                .background(Color.White)

        )
        LazyRow {
            itemsIndexed(
                state.hoursDay
            ) { _, item ->
                WeatherDetailedHoursDayItem(hoursDay = item)
            }
        }
    }
}