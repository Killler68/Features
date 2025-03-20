package com.example.features.weather.presentation.view

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.features.weather.domain.entities.WeatherData
import com.example.features.weather.presentation.models.WeatherState


@Composable
fun DailyWeatherItem(
    weatherWeek: WeatherData,
    state: WeatherState.Success,
    onClick: () -> Unit
) {
    val temperatureMax = "${weatherWeek.maxTemp.toInt()}°"
    val temperatureMin = "${weatherWeek.minTemp.toInt()}°"

    val textColor = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
    val cardColor = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.CARD)
    val image = weatherWeek.icon.imageWeatherExtension(weatherWeek.day)
    val day = weatherWeek.day.dateFormatDays()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(cardColor)
            .clickable { onClick() }
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = day,
                textAlign = TextAlign.Start,
                color = textColor,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 10.dp)
            )

            Text(
                text = temperatureMin,
                textAlign = TextAlign.End,
                color = textColor,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(0.3f)
            )

            Text(
                text = temperatureMax,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(horizontal = 5.dp),
                color = textColor
            )

            Image(
                painter = painterResource(image),
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

        LazyRow { items(weatherWeek.listWeek) { item -> HourlyWeatherItem(item, state) } }
    }
}