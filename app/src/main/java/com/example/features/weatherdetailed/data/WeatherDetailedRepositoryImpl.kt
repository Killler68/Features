package com.example.features.weatherdetailed.data

import com.example.features.common.api.WeatherRetrofitClient
import com.example.features.weather.domain.entities.WeatherDetailedDay
import com.example.features.weatherdetailed.domain.entities.WeatherHoursDay
import com.example.features.weatherdetailed.domain.usecase.WeatherDetailedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherDetailedRepositoryImpl : WeatherDetailedRepository {

    override suspend fun getWeatherDetailedDay(weatherId: Int, city: String): WeatherDetailedDay =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)
            val forecast =
                response.forecastList.find { it.dt == weatherId.toLong() }
                    ?: throw Exception("Данные не найдены")
            WeatherDetailedDay(
                city = response.city.name,
                dt = forecast.dt,
                dtText = forecast.dtTxt,
                temp = forecast.main.temp,
                description = forecast.weather.first().description,
                maxTemp = forecast.main.tempMax,
                minTemp = forecast.main.tempMin,
                feelingTemp = forecast.main.feelsLike,
                pressure = forecast.main.pressure,
                visibility = forecast.visibility,
                humidity = forecast.main.humidity,
                windDirection = forecast.wind.deg,
                windSpeed = forecast.wind.speed,
                probabilityPrecipitation = forecast.pop,
                partDay = forecast.sys.pod,
                sunSet = response.city.sunset,
                sunRise = response.city.sunrise
            )
        }

    override suspend fun getWeatherHoursDay(weatherId: Int, city: String): List<WeatherHoursDay> =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)

            val selectedDate = response.forecastList
                .find { it.dt == weatherId.toLong() }?.dtTxt?.substring(0, 10)
                ?: throw Exception("Данные не найдены")

            response.forecastList
                .filter { it.dtTxt.startsWith(selectedDate) }
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