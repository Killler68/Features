package com.example.features.weather.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.common.extension.firstUppercaseString
import com.example.features.common.extension.getRawNameCityEngToRuExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.strings.city
import com.example.features.common.utils.ColorCategory
import com.example.features.common.view.ErrorScreen
import com.example.features.common.view.LoadingScreen
import com.example.features.weather.model.WeatherEvent
import com.example.features.weather.model.WeatherSideEffect
import com.example.features.weather.model.WeatherState
import com.example.features.weather.screen.view.ChangeLocation
import com.example.features.weather.screen.view.WeatherList
import com.example.features.weather.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherScreen(navController: NavController) {

    val viewModel: WeatherViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    when (state) {
        WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> WeatherContent(
            state as WeatherState.Success,
            navController
        )

        is WeatherState.Error -> ErrorScreen(R.drawable.weather, "Ошибка")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherContent(
    state: WeatherState.Success,
    navController: NavController
) {
    val viewModel: WeatherViewModel = getViewModel()
    var editCity by remember { mutableStateOf(state.preview.city) }
    var isDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is WeatherSideEffect.NavigateTo -> navController.navigate(effect.route)

                WeatherSideEffect.Popup -> isDialogVisible = true
                WeatherSideEffect.None -> isDialogVisible = false
            }
        }
    }

    if (isDialogVisible) {
        ChangeLocation(
            title = editCity.getRawNameCityEngToRuExtension(),
            onCityChange = { editCity = it },
            onDismiss = { viewModel.dispatch(WeatherEvent.OnCloseShowDialogChangeCity) },
            state = state,
            onSave = {
                city = editCity.trim().firstUppercaseString()
                viewModel.loadWeather(city)
                viewModel.dispatch(WeatherEvent.OnCloseShowDialogChangeCity)
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
                            viewModel.dispatch(WeatherEvent.OnShowDialogChangeCity)
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

            WeatherList(
                paddingValues,
                state,
                viewModel
            )
        },
        containerColor = weatherColorExtension(
            state.weatherWeek.first().partDay,
            ColorCategory.BACKGROUND
        )
    )
}