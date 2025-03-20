package com.example.features.welcome.data

import com.example.features.R
import com.example.features.welcome.domain.entities.PagerItems
import com.example.features.welcome.domain.usecase.WelcomeRepository

class WelcomeRepositoryImpl : WelcomeRepository {

    override fun getPagerItem(): List<PagerItems> = pagerItems

    private val pagerItems = listOf(
        PagerItems(
            image = R.drawable.book,
            title = "Заметки",
            subTitle = "Создание заметок с подробным описанием"
        ),
        PagerItems(
            image = R.drawable.weather_forecast,
            title = "Погода",
            subTitle = "Узнайте погоду в любой точке мира!"
        ),
        PagerItems(
            image = R.drawable.trash_bucket,
            title = "В стадии разработки",
            subTitle = ""
        )
    )
}