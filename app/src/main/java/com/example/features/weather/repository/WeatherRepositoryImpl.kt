package com.example.features.weather.repository

import androidx.compose.runtime.mutableStateOf
import com.example.features.common.api.WeatherRetrofitClient
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherAdditionalInfoDay
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

    override suspend fun getWeatherDetailedDay(weatherId: Int): WeatherDetailedDay {
        TODO()
//        withContext(Dispatchers.IO) {
//            val response =
//                WeatherRetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, weatherId)
//            WeatherDetailedDay(
//                response.location.name,
//                response.current.temp_c,
//                response.current.condition.text,
//                response.forecast.forecastday.first().day.maxtemp_c,
//                response.forecast.forecastday.first().day.mintemp_c,
//                response.forecast.forecastday.first().day.avgtemp_c
//            )
    }

    override suspend fun getWeatherHoursDay(weatherId: Int): List<WeatherHoursDay> {
        TODO()
//        withContext(Dispatchers.IO) {
//            val response =
//                WeatherRetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, weatherId)
//            val days = response.forecast.forecastday
//            days.flatMap { day ->
//                day.hour.map { hour ->
//                    WeatherHoursDay(
//                        hour.time.dateFormatHourly(),
//                        hour.condition.icon,
//                        hour.temp_c,
//                        day.day.daily_chance_of_rain
//                    )
//                }
//            }
    }

    override suspend fun getWeatherAdditionalInfoDay(weatherId: Int): WeatherAdditionalInfoDay {
        TODO()

//        withContext(Dispatchers.IO) {
//            val response =
//                WeatherRetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, weatherId)
//            WeatherAdditionalInfoDay(
//                response.current.temp_c,
//                response.current.condition.text,
//                response.uv,
//                response.humidity,
//                response.pressure_mb,
//                response.wind_kph
//            )
//        }
    }
}
