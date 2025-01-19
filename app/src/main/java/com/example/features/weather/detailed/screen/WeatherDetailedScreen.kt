package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.navigation.Screens
import com.example.features.ui.theme.WeatherBackground
import com.example.features.weather.detailed.viewmodel.WeatherDetailedViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailedScreen(navController: NavController) {

    val viewModel: WeatherDetailedViewModel = getViewModel()
    val weatherDetailed = viewModel.detailedDay.value
    val weatherAdditionalInfoDay = viewModel.additionalInfoDay.value

    LaunchedEffect(Unit) {
        viewModel.loadWeatherDetailedDay()
        viewModel.loadWeatherAdditionalInfoDay()
        viewModel.loadWeatherHoursDay()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarScreen(
                        R.drawable.back,
                        { navController.navigate(Screens.Weather.route) },
                        "back",
                        "London",
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = WeatherBackground)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 10.dp)
            ) {
                weatherDetailed.apply {
                    TextPreview(
                        temp = temp,
                        description = descriptionWeather,
                        maxTemp = maxTemp,
                        minTemp = minTemp,
                        feelingTemp = feelingTemp
                    )
                }

                HoursInfoDay(
                    description = weatherDetailed.descriptionWeather
                )
                weatherAdditionalInfoDay.apply {
                    AdditionalInfoDay(
                        uvIndex = temp,
                        humidity = temp,
                        wind = temp,
                        pressure = temp
                    )
                }
                WeatherNameAPI()
            }
        },
        containerColor = WeatherBackground
    )
}