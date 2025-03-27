package com.example.features.weather.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.features.R
import com.example.features.common.extension.dateFormatPreview
import com.example.features.common.extension.extensionConditionWeather
import com.example.features.common.extension.capitalizeFirstLetter
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.domain.entities.WeatherPreview
import com.example.features.weather.presentation.models.WeatherState


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherPreview(preview: WeatherPreview, state: WeatherState.Success) {

    val temperature = preview.temp.toInt()
    val textColor = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
    val cardColor = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.CARD)
    val image = preview.icon.extensionConditionWeather(state.weatherWeek.first().partDay)
    val description = preview.description.capitalizeFirstLetter().getRawNameWeatherExtension()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = preview.date.dateFormatPreview(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = textColor,
        )

        GlideImage(
            model = image,
            contentDescription = stringResource(R.string.animated_gif),
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(100.dp),
            contentScale = ContentScale.Inside
        )
        Text(
            text = stringResource(R.string.celsius_degree, temperature),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 30.sp,
            color = textColor,
        )
        Text(
            text = description,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = textColor,
        )
    }
}