package com.example.features.weather.data

import com.example.features.common.api.WeatherRetrofitClient
import com.example.features.weather.domain.entities.WeatherPreviewBar
import com.example.features.weather.domain.entities.WeatherWeek
import com.example.features.weather.domain.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl : WeatherRepository {

    override suspend fun getWeatherWeek(city: String): List<WeatherWeek> =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)
            response.forecastList.map {
                WeatherWeek(
                    it.dt,
                    it.dtTxt,
                    it.main.temp,
                    it.main.tempMax,
                    it.main.tempMin,
                    it.weather.first().icon,
                    it.sys.pod,
                )
            }
        }

    override suspend fun weatherPreviewBar(city: String): WeatherPreviewBar =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)
            val firstForecast = response.forecastList.firstOrNull()
            WeatherPreviewBar(
                city = response.city.name,
                date = firstForecast?.dt ?: 0,
                dtText = firstForecast?.dtTxt ?: "",
                icon = firstForecast?.weather?.firstOrNull()?.icon ?: "",
                temp = firstForecast?.main?.temp ?: 0.0,
                description = firstForecast?.weather?.firstOrNull()?.description ?: "",
                partDay = firstForecast?.sys?.pod ?: ""
            )
        }
}

