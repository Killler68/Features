package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weatherdetailed.domain.entities.WeatherHoursDay
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState

@Composable
fun WeatherDetailedHoursDayItem(hoursDay: WeatherHoursDay, state: WeatherDetailedState.Success) {

    val cardColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.CARD)
    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)

    val hours = hoursDay.hours.dateFormatHours()
    val temp = hoursDay.temp.toInt()
    val iconRes = remember(hoursDay.icon, hoursDay.hours) {
        hoursDay.icon.imageWeatherExtension(hoursDay.hours)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
            .background(cardColor)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = hours,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 5.dp),
            color = textColor
        )

        Image(
            painter = painterResource(iconRes),
            contentDescription = stringResource(R.string.hours_weather),
            modifier = Modifier.size(28.dp)
        )

        Text(
            text = stringResource(R.string.celsius_degree, temp),
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            color = textColor
        )

        WeatherDetailedRainDrop(state, hoursDay)
    }
}
