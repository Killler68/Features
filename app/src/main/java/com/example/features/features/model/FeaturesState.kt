package com.example.features.features.model

import com.example.features.notes.common.model.NotesModel
import com.example.features.weather.model.WeatherPreviewBar

sealed class FeaturesState {

    data object Loading : FeaturesState()
    data class Success(
        val itemDrawer: List<DrawerItems>,
        val itemWeather: WeatherPreviewBar?,
        val itemNote: List<NotesModel>,
        val itemFeature: List<Features>,
        val isNotesLoading: Boolean = true,
        val isWeatherLoading: Boolean = true
    ) : FeaturesState()

    data class Error(val message: String) : FeaturesState()
}