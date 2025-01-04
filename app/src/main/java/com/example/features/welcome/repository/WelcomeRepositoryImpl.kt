package com.example.features.welcome.repository

import com.example.features.R
import com.example.features.welcome.models.PagerItems
import com.example.features.welcome.usecase.WelcomeRepository

class WelcomeRepositoryImpl : WelcomeRepository {

    override fun getPagerItem(): List<PagerItems> = test

    private val test = listOf(
        PagerItems(
            R.drawable.book,
            "Заметки",
            "Создание заметок с подробным описанием"
        ),
        PagerItems(
            R.drawable.weather_forecast,
            "Погода",
            "Узнайте погоду в любой точке мира!"
        ),
        PagerItems(
            R.drawable.trash_bucket,
            "В стадии разработки",
            ""
        ),
    )
}