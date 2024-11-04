package com.example.features.features.repository

import com.example.features.features.model.Features
import com.example.features.features.usecase.FeaturesRepository
import com.example.features.navigation.Screens

class FeaturesRepositoryImpl : FeaturesRepository {

    override fun getFeatures(): List<Features> = features
}

private val features = listOf(
    Features("Notes", "Заметки", Screens.NotesList.route),
    Features("Weather", "Погода", Screens.Weather.route),
)