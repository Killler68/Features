package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weatherdetailed.domain.entities.WeatherHoursDay
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState


@Composable
fun WeatherDetailedRainDrop(state: WeatherDetailedState.Success, hoursDay: WeatherHoursDay) {

    val chanceRain = (hoursDay.chanceRain).toInt()
    val imageColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.IMAGE)
    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
    Row {
        Image(
            painter = painterResource(R.drawable.rain_drop),
            contentDescription = stringResource(R.string.rain_drop),
            colorFilter = ColorFilter.tint(imageColor),
            modifier = Modifier
                .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
                .size(8.dp),
        )

        Text(
            text = stringResource(R.string.percent, chanceRain),
            fontSize = 10.sp,
            color = textColor
        )
    }
}