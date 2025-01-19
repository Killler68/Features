package com.example.features.weather.detailed.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
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
                .padding(vertical = 5.dp),
            color = Color.White
        )
        Image(
            painter = rememberAsyncImagePainter("https:" + hoursDay.icon),
            contentDescription = "hours_weather",
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = hoursDay.temp.toString(),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(vertical = 5.dp),
            color = Color.White
        )
        Text(
            text = hoursDay.rainfall.toString(),
            fontSize = 12.sp,
            color = Color.White
        )
    }
}