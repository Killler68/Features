package com.example.features.features.repository

import com.example.features.R
import com.example.features.features.model.DrawerItems
import com.example.features.features.model.Features
import com.example.features.features.model.FeaturesItemDrawer
import com.example.features.features.usecase.FeaturesRepository
import com.example.features.common.navigation.Screens

class FeaturesRepositoryImpl : FeaturesRepository {

    override fun getFeatures(): List<Features> = features
    override fun getDrawerItems(): List<DrawerItems> = drawerItems
}

private val features = listOf(
    Features(
        "Перейдите и посмотрите что в задачах",
        "Создайте заметку или задачу",
        "",
        0.0f,
        Screens.NotesList.route
    ),
    Features(
        "Погода сегодня прекрасна не так ли?",
        "Выберите город для отображения погоды",
        "",
        0.0f,
        Screens.Weather.route
    )
)

private val drawerItems = listOf(
    DrawerItems(FeaturesItemDrawer.PROFILE_PREVIEW, "Профиль", R.drawable.profile),
    DrawerItems(FeaturesItemDrawer.PROFILE, "Настройки", 0),
    DrawerItems(FeaturesItemDrawer.SETTINGS, "Настройки", R.drawable.settings),
    DrawerItems(FeaturesItemDrawer.ABOUT, "О приложении", R.drawable.question),
)