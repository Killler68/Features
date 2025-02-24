package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.extension.weatherVisibilityExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedEvent
import com.example.features.weather.detailed.model.WeatherDetailedSideEffect
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.weather.detailed.viewmodel.WeatherDetailedViewModel
import com.example.features.weather.screen.LoadingScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherDetailedScreen(weatherId: Int, navController: NavController) {

    val viewModel: WeatherDetailedViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        viewModel.dispatch(WeatherDetailedEvent.LoadData(weatherId = weatherId))
        effectFlow.collect { effect ->
            when (effect) {
                is WeatherDetailedSideEffect.ToBack -> navController.popBackStack()
            }
        }
    }
    when (state) {
        WeatherDetailedState.Loading -> LoadingScreen()
        is WeatherDetailedState.Success -> WeatherDetailedContent(state as WeatherDetailedState.Success)
        is WeatherDetailedState.Error -> Text(("error"))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailedContent(
    state: WeatherDetailedState.Success
) {

    val viewModel: WeatherDetailedViewModel = getViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarScreen(
                        imageOnBack = R.drawable.back,
                        onBack = { viewModel.dispatch(WeatherDetailedEvent.ToBack) },
                        imageDescriptionOnBack = "back",
                        city = state.detailedDay.city,
                        color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = weatherColorExtension(
                        state.detailedDay.partDay,
                        ColorCategory.BACKGROUND
                    )
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 10.dp)
            ) {
                state.detailedDay.apply {
                    TextPreview(
                        temp = temp,
                        description = description,
                        maxTemp = maxTemp,
                        minTemp = minTemp,
                        feelingTemp = feelingTemp,
                        state
                    )
                }

                HoursInfoDay(state = state)

                state.detailedDay.apply {
                    AdditionalInfoDay(
                        visibility = weatherVisibilityExtension(visibility),
                        humidity = humidity,
                        wind = windSpeed,
                        pressure = pressure,
                        state
                    )
                }
                WeatherNameAPI(state)
            }
        },
        containerColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.BACKGROUND)
    )
}
