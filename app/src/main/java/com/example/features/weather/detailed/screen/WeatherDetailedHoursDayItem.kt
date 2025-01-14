package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.weather.model.WeatherHoursDay

@Composable
fun WeatherDetailedHoursDayItem(hoursDay: WeatherHoursDay) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = hoursDay.hours,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        Text(
            text = hoursDay.image,
            fontSize = 12.sp
        )
        Text(
            text = hoursDay.temp,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        Text(
            text = hoursDay.rainfall,
            fontSize = 12.sp
        )
    }
}