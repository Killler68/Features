package com.example.features.weather.state

sealed class WeatherEvent {

    data object OnBackClick: WeatherEvent()

}