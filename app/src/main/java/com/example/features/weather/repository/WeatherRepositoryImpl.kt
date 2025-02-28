package com.example.features.weather.repository

import com.example.features.common.api.WeatherRetrofitClient
import com.example.features.weather.detailed.model.WeatherHoursDay
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl : WeatherRepository, WeatherDetailedRepository {

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

    override suspend fun previewBarWeather(city: String): PreviewBarWeather =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)
            val firstForecast = response.forecastList.firstOrNull()
            PreviewBarWeather(
                city = response.city.name,
                date = firstForecast?.dt ?: 0,
                dtText = firstForecast?.dtTxt ?: "",
                icon = firstForecast?.weather?.firstOrNull()?.icon ?: "",
                temp = firstForecast?.main?.temp ?: 0.0,
                description = firstForecast?.weather?.firstOrNull()?.description ?: "",
                partDay = firstForecast?.sys?.pod ?: ""
            )
        }

    override suspend fun getWeatherDetailedDay(weatherId: Int, city: String): WeatherDetailedDay =
        withContext(Dispatchers.IO) {
            val response = WeatherRetrofitClient.weatherApi.getWeather(city = city)
            val forecast =
                response.forecastList.find { it.dt == weatherId.toLong() }
                    ?: throw Exception("Данные не найдены")
            WeatherDetailedDay(
                city = response.city.name,
                dtText = forecast.dt,
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

