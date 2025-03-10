package com.example.features.weather.screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.features.common.extension.dateFormatDays
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.model.WeatherData
import com.example.features.weather.model.WeatherState


@Composable
fun DailyWeatherItem(
    weatherWeek: WeatherData,
    state: WeatherState.Success,
    onClick: () -> Unit
) {
    val temperatureMax = "${weatherWeek.maxTemp.toInt()}°"
    val temperatureMin = "${weatherWeek.minTemp.toInt()}°"

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                weatherColorExtension(
                    state.weatherWeek.first().partDay,
                    ColorCategory.CARD
                )
            )
            .clickable { onClick() }
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = weatherWeek.day.dateFormatDays(),
                textAlign = TextAlign.Start,
                color = weatherColorExtension(
                    state.weatherWeek.first().partDay,
                    ColorCategory.TEXT
                ),
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 10.dp)
            )

            Text(
                text = temperatureMin,
                textAlign = TextAlign.End,
                color = weatherColorExtension(
                    state.weatherWeek.first().partDay,
                    ColorCategory.TEXT
                ),

                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(0.3f)
            )

            Text(
                text = temperatureMax,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(horizontal = 5.dp),
                color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)

            )

            Image(
                painter = painterResource(weatherWeek.icon.imageWeatherExtension(weatherWeek.day)),
                contentDescription = "image",
                modifier = Modifier
                    .padding(start = 5.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                    .size(18.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .background(Color.White)
                .height(1.dp)
        )

        ListHoursWeather(
            weather = weatherWeek,
            state = state
        )
    }
}