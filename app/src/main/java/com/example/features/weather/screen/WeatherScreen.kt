package com.example.features.weather.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.features.R
import com.example.features.common.extension.dateFormatDays
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.dateFormatPreview
import com.example.features.common.extension.getRawNameCityEngToRuExtension
import com.example.features.common.extension.getRawNameCityRuToEngExtension
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherData
import com.example.features.weather.model.WeatherEvent
import com.example.features.weather.model.WeatherSideEffect
import com.example.features.weather.model.WeatherState
import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.repository.city
import com.example.features.weather.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherScreen(navController: NavController) {

    val viewModel: WeatherViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is WeatherSideEffect.NavigateTo -> navController.navigate(effect.route)
            }
        }
    }

    when (state) {
        WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> WeatherContent(state as WeatherState.Success)
        is WeatherState.Error -> ErrorScreen(R.drawable.weather, "Ошибка")
    }
}

@Composable
fun WeatherContent(state: WeatherState.Success) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsWeatherActionBar(state.preview)
        DsWeatherPreviewBar(state.preview)
        DsDailyWeatherPanel(state.weatherWeek)
    }
}

@Composable
fun DsWeatherActionBar(preview: PreviewBarWeather) {

    val viewModel: WeatherViewModel = getViewModel()
    var editCity by remember { mutableStateOf(preview.city) }

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "image_back",
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
                .clickable { viewModel.dispatch(WeatherEvent.ToBack) }
        )

        Text(
            text = preview.city.getRawNameCityEngToRuExtension(),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(0.5f)
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .weight(0.1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "image_location",
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGray)
                    .padding(7.dp)
                    .clickable { viewModel.isEnabled.value = true }
            )

            if (viewModel.isEnabled.value) {
                DsChangeLocation(
                    title = editCity.getRawNameCityEngToRuExtension(),
                    onCityChange = { editCity = it },
                    onDismiss = { viewModel.isEnabled.value = false },
                    onSave = {
                        preview.city = editCity

                        city.value = editCity.getRawNameCityRuToEngExtension()

                        viewModel.dispatch(WeatherEvent.LoadData)
                        viewModel.isEnabled.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun DsWeatherPreviewBar(preview: PreviewBarWeather) {

    val temperature = "${preview.temp.toInt()}°"

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = preview.date.dateFormatPreview(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = painterResource(preview.icon.imageWeatherExtension()),
            contentDescription = "image",
            modifier = Modifier
                .size(100.dp)
        )
        Text(
            text = temperature,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            text = preview.description.getRawNameWeatherExtension(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun DsDailyWeatherPanel(weatherWeek: List<WeatherData>) {
    val viewModel: WeatherViewModel = getViewModel()

    LazyColumn {
        items(weatherWeek) { item ->
            DsDailyWeatherItem(
                weatherWeek = item,
                weather = item.listWeek
            ) {
                viewModel.dispatch(WeatherEvent.ToWeatherDetailed(item.day))
            }
        }
    }
}

@Composable
fun DsDailyWeatherItem(
    weather: List<WeatherWeek>,
    weatherWeek: WeatherData,
    onClick: () -> Unit
) {
    val temperatureMax = "${weatherWeek.maxTemp.toInt()}°"
    val temperatureMin = "${weatherWeek.minTemp.toInt()}°"

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
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
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 10.dp)
            )

            Text(
                text = temperatureMin,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(0.3f)
            )

            Text(
                text = temperatureMax,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Image(
                painter = rememberAsyncImagePainter("https:" + weatherWeek.icon),
                contentDescription = "image",
                modifier = Modifier.size(30.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .background(Color.White)
                .height(1.dp)
        )

        LazyRow(
            Modifier.clip(RoundedCornerShape(20.dp))
        ) {
            items(weather) { item ->
                DsHourlyWeatherItem(hourWeather = item)
            }
        }
    }
}

@Composable
fun DsHourlyWeatherItem(hourWeather: WeatherWeek) {
    val temperature = "${hourWeather.temp.toInt()}°"

    Column(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = hourWeather.day.dateFormatHours(),
            fontSize = 12.sp
        )
        Image(
            painter = painterResource(hourWeather.icon.imageWeatherExtension()),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
        )
        Text(
            text = temperature,
            fontSize = 12.sp
        )
    }
}
