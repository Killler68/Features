package com.example.features.weather.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.common.view.ErrorScreen
import com.example.features.common.view.LoadingScreen
import com.example.features.weather.presentation.models.WeatherSideEffect
import com.example.features.weather.presentation.models.WeatherState
import com.example.features.weather.presentation.view.IsChangeLocationView
import com.example.features.weather.presentation.view.WeatherList
import com.example.features.weather.presentation.view.WeatherTopBar
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherScreen(navController: NavController) {

    val viewModel: WeatherViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is WeatherSideEffect.NavigateTo -> navController.navigate(effect.route)

                WeatherSideEffect.Popup -> isDialogVisible = true
                WeatherSideEffect.None -> isDialogVisible = false
                WeatherSideEffect.ToBack -> navController.popBackStack()
            }
        }
    }

    when (state) {
        WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> WeatherContent(
            state as WeatherState.Success,
            isDialogVisible
        )

        is WeatherState.Error -> ErrorScreen(R.drawable.weather, "Ошибка")
    }
}

@Composable
fun WeatherContent(
    state: WeatherState.Success,
    isDialogVisible: Boolean
) {
    val viewModel: WeatherViewModel = getViewModel()

    val backgroundColor =
        weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.BACKGROUND)

    IsChangeLocationView(isDialogVisible, state)

    Scaffold(
        topBar = { WeatherTopBar(viewModel, state) },
        content = { WeatherList(paddingValues = it, state) },
        containerColor = backgroundColor
    )
}

