package com.example.features.weather.repository

import androidx.compose.runtime.mutableStateOf
import com.example.features.common.api.WeatherRetrofitClient
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay
import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

var city = mutableStateOf("London   ")

class WeatherRepositoryImpl : WeatherRepository, WeatherDetailedRepository {

    override suspend fun getWeatherWeek(): List<WeatherWeek> =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather()
            response.forecastList.map {
                WeatherWeek(
                    it.dt,
                    it.dtTxt,
                    it.main.temp,
                    it.main.tempMax,
                    it.main.tempMin,
                    it.weather.first().icon
                )
            }
        }

    override suspend fun previewBarWeather(): PreviewBarWeather =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather()
            val firstForecast = response.forecastList.firstOrNull()
            PreviewBarWeather(
                city = response.city.name,
                date = firstForecast?.dt ?: 0,
                icon = firstForecast?.weather?.firstOrNull()?.icon ?: "",
                temp = firstForecast?.main?.temp ?: 0.0,
                description = firstForecast?.weather?.firstOrNull()?.description ?: ""
            )
        }

    override suspend fun getWeatherDetailedDay(weatherId: Int): WeatherDetailedDay =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather()
            val forecast =
                response.forecastList.find { it.dt == weatherId.toLong() }
                    ?: throw Exception("Данные не найдены")
            WeatherDetailedDay(
                city = response.city.name,
                temp = forecast.main.temp,
                description = forecast.weather.first().description,
                maxTemp = forecast.main.tempMax,
                minTemp = forecast.main.tempMin,
                feelingTemp = forecast.main.feelsLike,
                pressure = forecast.main.pressure,
                humidity = forecast.main.humidity,
                windDirection = forecast.wind.deg,
                windSpeed = forecast.wind.speed,
                probabilityPrecipitation = forecast.pop,
                partDay = forecast.sys.pod
            )
        }

    override suspend fun getWeatherHoursDay(weatherId: Int): List<WeatherHoursDay> =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather()
            response.forecastList
                .filter { it.dt == weatherId.toLong() }
                .map {
                    WeatherHoursDay(
                        it.dt,
                        it.weather.first().icon,
                        it.main.temp,
                        it.pop
                    )
                }
        }
}

