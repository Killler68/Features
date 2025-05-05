package com.example.features.features.presentation.models

import com.example.features.features.domain.entities.DrawerItem
import com.example.features.features.domain.entities.Features
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.weather.domain.entities.WeatherPreview

sealed class FeaturesState {

    data object Loading : FeaturesState()
    data class Success(
        val itemDrawer: List<DrawerItem>,
        val itemWeather: WeatherPreview?,
        val itemNote: List<NoteTaskItem>,
        val itemFeature: List<Features>,
        val isNotesLoading: Boolean = true,
        val isWeatherLoading: Boolean = true
    ) : FeaturesState()

    data class Error(val message: Int) : FeaturesState()
}