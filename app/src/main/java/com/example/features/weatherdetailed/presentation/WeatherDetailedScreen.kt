package com.example.features.weatherdetailed.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.common.view.ErrorScreen
import com.example.features.common.view.LoadingScreen
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedEvent
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedSideEffect
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState
import com.example.features.weatherdetailed.presentation.view.WeatherDetailedScaffoldContent
import com.example.features.weatherdetailed.presentation.view.WeatherDetailedTopBar
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
        is WeatherDetailedState.Error -> ErrorScreen(
            R.drawable.weather,
            stringResource(R.string.error_load)
        )
    }
}

@Composable
fun WeatherDetailedContent(state: WeatherDetailedState.Success) {

    val viewModel: WeatherDetailedViewModel = getViewModel()
    val backgroundColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.BACKGROUND)
    Scaffold(
        topBar = {
            WeatherDetailedTopBar(
                onBack = { viewModel.dispatch(WeatherDetailedEvent.ToBack) },
                state = state
            )
        },
        content = { WeatherDetailedScaffoldContent(paddingValues = it, state) },
        containerColor = backgroundColor
    )
}

