package com.example.features.weather.screen.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.strings.city
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.model.WeatherEvent
import com.example.features.weather.model.WeatherState
import com.example.features.weather.viewmodel.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(viewModel: WeatherViewModel, state: WeatherState.Success) {

    val textColor = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
    val imageColor = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.IMAGE)
    val backgroundColor =
        weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.BACKGROUND)

    TopAppBar(
        title = {
            TopBarScreen(
                imageOnBack = R.drawable.back,
                onBack = { viewModel.dispatch(WeatherEvent.ToBack) },
                imageDescriptionOnBack = "back",
                imageColor = imageColor,
                city = city,
                textColor = textColor,
                image = R.drawable.location,
                imageDescription = "city",
                onClick = {
                    viewModel.dispatch(WeatherEvent.OnShowDialogChangeCity)
                }
            )
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
    )
}