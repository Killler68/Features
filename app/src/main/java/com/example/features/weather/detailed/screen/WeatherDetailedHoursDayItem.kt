package com.example.features.weather.detailed.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.features.R
import com.example.features.common.extension.dateFormatHours
import com.example.features.ui.theme.LightGray
import com.example.features.weather.model.WeatherHoursDay

@Composable
fun WeatherDetailedHoursDayItem(hoursDay: WeatherHoursDay) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = hoursDay.hours.dateFormatHours(),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(vertical = 5.dp),
            color = Color.White
        )
        Image(
            painter = rememberAsyncImagePainter("https:" + hoursDay.icon),
            contentDescription = "hours_weather",
            modifier = Modifier
                .size(28.dp)
        )
        Text(
            text = "${hoursDay.temp.toInt()}°",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            color = Color.White
        )

        Row {

            Image(
                painter = painterResource(R.drawable.rain_drop),
                contentDescription = "rain_drop",
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
                    .size(8.dp),
                colorFilter = ColorFilter.tint(LightGray)
            )

            Text(
                text = "${hoursDay.chanceRain}%",
                fontSize = 10.sp,
                color = Color.White
            )
        }

    }
}