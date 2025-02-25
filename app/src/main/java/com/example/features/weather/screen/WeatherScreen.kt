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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.features.common.design.TopBarScreen
import com.example.features.common.extension.dateFormatDays
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.dateFormatPreview
import com.example.features.common.extension.firstUppercaseString
import com.example.features.common.extension.getRawNameCityEngToRuExtension
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.strings.city
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherData
import com.example.features.weather.model.WeatherEvent
import com.example.features.weather.model.WeatherSideEffect
import com.example.features.weather.model.WeatherState
import com.example.features.weather.model.WeatherWeek
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherContent(state: WeatherState.Success) {

    val viewModel: WeatherViewModel = getViewModel()
    var editCity by remember { mutableStateOf(state.preview.city) }

    if (viewModel.isEnabled.value) {
        DsChangeLocation(
            title = editCity.getRawNameCityEngToRuExtension(),
            onCityChange = { editCity = it },
            onDismiss = { viewModel.isEnabled.value = false },
            onSave = {
                city = editCity
                viewModel.dispatch(WeatherEvent.LoadData)
                viewModel.isEnabled.value = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarScreen(
                        imageOnBack = R.drawable.back,
                        onBack = { viewModel.dispatch(WeatherEvent.ToBack) },
                        imageDescriptionOnBack = "back",
                        imageColor = weatherColorExtension(
                            state.weatherWeek.first().partDay,
                            ColorCategory.IMAGE
                        ),
                        city = city,
                        textColor = weatherColorExtension(
                            state.weatherWeek.first().partDay,
                            ColorCategory.TEXT
                        ),
                        image = R.drawable.location,
                        imageDescription = "city",
                        onClick = {
                            viewModel.isEnabled.value = true
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = weatherColorExtension(
                        state.weatherWeek.first().partDay,
                        ColorCategory.BACKGROUND
                    )
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    DsWeatherPreviewBar(state.preview, state)
                }
                items(state.weatherWeek) { weatherData ->
                    DsDailyWeatherItem(
                        weatherWeek = weatherData,
                        weather = weatherData.listWeek,
                        state = state
                    ) {
                        viewModel.dispatch(WeatherEvent.ToWeatherDetailed(weatherData.day))
                    }
                }
            }
        },
        containerColor = weatherColorExtension(
            state.weatherWeek.first().partDay,
            ColorCategory.BACKGROUND
        )
    )
}

@Composable
fun DsWeatherPreviewBar(preview: PreviewBarWeather, state: WeatherState.Success) {

    val temperature = "${preview.temp.toInt()}°"

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                weatherColorExtension(
                    state.weatherWeek.first().partDay,
                    ColorCategory.CARD
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = preview.date.dateFormatPreview(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT),
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
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT),
        )
        Text(
            text = preview.description.firstUppercaseString().getRawNameWeatherExtension(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT),
        )
    }
}

@Composable
fun DsDailyWeatherItem(
    weather: List<WeatherWeek>,
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
                DsHourlyWeatherItem(hourWeather = item, state)
            }
        }
    }
}

@Composable
fun DsHourlyWeatherItem(hourWeather: WeatherWeek, state: WeatherState.Success) {
    val temperature = "${hourWeather.temp.toInt()}°"

    Column(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                weatherColorExtension(
                    state.weatherWeek.first().partDay,
                    ColorCategory.CARD
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = hourWeather.day.dateFormatHours(),
            fontSize = 12.sp,
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
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
            fontSize = 12.sp,
            color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
        )
    }
}
