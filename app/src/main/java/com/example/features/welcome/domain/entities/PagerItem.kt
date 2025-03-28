package com.example.features.welcome.domain.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.features.R

sealed class PagerItem(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val subTitle: Int
) {

    data object NoteItem : PagerItem(
        image = R.drawable.book,
        title = R.string.notes_title,
        subTitle = R.string.notes_subtitle
    )

    data object WeatherItem : PagerItem(
        image = R.drawable.weather_forecast,
        title = R.string.weather_title,
        subTitle = R.string.weather_subtitle
    )

    data object EmptyItem : PagerItem(
        image = R.drawable.trash_bucket,
        title = R.string.empty_title,
        subTitle = R.string.empty_subtitle
    )
}
