package com.example.features.weather.detailed.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.weather.detailed.model.WeatherHoursDay

@Composable
fun WeatherDetailedHoursDayItem(hoursDay: WeatherHoursDay, state: WeatherDetailedState.Success) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
            .background(weatherColorExtension(state.detailedDay.partDay, ColorCategory.CARD))
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = hoursDay.hours.dateFormatHours(),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(vertical = 5.dp),
            color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
        )
        Image(
            painter = painterResource(hoursDay.icon.imageWeatherExtension()),
            contentDescription = "hours_weather",
            modifier = Modifier
                .size(28.dp)
        )
        Text(
            text = "${hoursDay.temp.toInt()}°",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
        )

        Row {
            Image(
                painter = painterResource(R.drawable.rain_drop),
                contentDescription = "rain_drop",
                colorFilter = ColorFilter.tint(
                    weatherColorExtension(
                        state.detailedDay.partDay,
                        ColorCategory.IMAGE
                    )
                ),
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
                    .size(8.dp),
            )

            Text(
                text = "${(hoursDay.chanceRain * 100).toInt()}%",
                fontSize = 10.sp,
                color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
            )
        }
    }
}