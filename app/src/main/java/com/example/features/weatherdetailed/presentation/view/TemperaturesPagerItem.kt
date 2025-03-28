package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.formatToDayString
import com.example.features.common.extension.capitalizeFirstLetter
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weatherdetailed.domain.entities.TemperatureItem
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState


@Composable
fun TemperaturesPagerItem(temperature: TemperatureItem, state: WeatherDetailedState.Success) {

    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
    val formattedDifferenceText = temperature.differenceText.capitalizeFirstLetter()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = temperature.date.formatToDayString(),
            fontSize = 16.sp,
            color = textColor
        )
        Text(
            text = formattedDifferenceText,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}