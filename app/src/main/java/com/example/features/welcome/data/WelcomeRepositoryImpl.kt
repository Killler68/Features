package com.example.features.welcome.data

import com.example.features.welcome.domain.entities.PagerItem
import com.example.features.welcome.domain.usecase.WelcomeRepository

class WelcomeRepositoryImpl : WelcomeRepository {

    override fun getPagerItem(): List<PagerItem> = listOf(
        PagerItem.NoteItem,
        PagerItem.WeatherItem,
        PagerItem.EmptyItem
    )
}