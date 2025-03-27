package com.example.features.weather.data

import com.example.features.common.api.WeatherRetrofitClient
import com.example.features.weather.domain.entities.WeatherPreview
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

    override suspend fun getWeatherPreview(city: String): WeatherPreview =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)
            val firstForecast = response.forecastList.firstOrNull()
            WeatherPreview(
                city = response.city.name,
                date = firstForecast?.dt ?: 0,
                dtText = firstForecast?.dtTxt ?: EMPTY_STRING,
                icon = firstForecast?.weather?.firstOrNull()?.icon ?: EMPTY_STRING,
                temp = firstForecast?.main?.temp ?: DEFAULT_TEMP,
                description = firstForecast?.weather?.firstOrNull()?.description ?: EMPTY_STRING,
                partDay = firstForecast?.sys?.pod ?: EMPTY_STRING
            )
        }

    companion object {
        const val EMPTY_STRING = ""
        const val DEFAULT_TEMP = 0.0
    }
}

