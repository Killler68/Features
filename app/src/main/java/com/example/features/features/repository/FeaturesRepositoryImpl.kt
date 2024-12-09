package com.example.features.features.repository

import com.example.features.R
import com.example.features.features.model.DrawerItems
import com.example.features.features.model.Features
import com.example.features.features.usecase.FeaturesRepository
import com.example.features.navigation.Screens

class FeaturesRepositoryImpl : FeaturesRepository {

    override fun getFeatures(): List<Features> = features
    override fun getDrawerItems(): List<DrawerItems> = drawerItems
}

private val features = listOf(
    Features("Notes", "Заметки", "", Screens.NotesList.route),
    Features("Weather", "Погода", "", Screens.Weather.route),
)

private val drawerItems = listOf(
    DrawerItems(0, "Профиль", R.drawable.location),
    DrawerItems(1, "Настройки", 0),
    DrawerItems(2, "Настройки", R.drawable.pencil),
)