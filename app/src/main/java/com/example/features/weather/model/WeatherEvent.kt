package com.example.features.weather.model

sealed class WeatherEvent {

    data object ToBack : WeatherEvent()
    data class ToWeatherDetailed(val weatherId: Long) : WeatherEvent()
    data object LoadData : WeatherEvent()
    data object OnShowDialogChangeCity : WeatherEvent()
    data object OnCloseShowDialogChangeCity : WeatherEvent()
}