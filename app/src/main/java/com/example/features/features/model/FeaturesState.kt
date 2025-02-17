package com.example.features.features.model

import com.example.features.notes.common.model.NotesModel
import com.example.features.weather.model.PreviewBarWeather

sealed class FeaturesState {

    data object Loading : FeaturesState()
    data class Success(
        val itemDrawer: List<DrawerItems>,
        val itemWeather: PreviewBarWeather,
        val itemNote: List<NotesModel>,
        val itemFeature: List<Features>
    ) : FeaturesState()

    data class Error(val message: String) : FeaturesState()
}