package com.example.features.weatherdetailed.presentation.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailedTopBar(
    onBack: () -> Unit,
    state: WeatherDetailedState.Success,
) {
    val city = state.detailedDay.city
    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
    val backgroundColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.BACKGROUND)

    TopAppBar(
        title = {
            TopBarScreen(
                imageOnBack = R.drawable.back,
                onBack = { onBack() },
                imageDescriptionOnBack = stringResource(R.string.back_image_description),
                city = city,
                textColor = textColor
            )
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
    )
}