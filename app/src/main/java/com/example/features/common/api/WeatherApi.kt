package com.example.features.common.api

import com.example.features.weather.domain.entities.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val OPEN_WEATHER_MAP = "https://api.openweathermap.org/"
const val WEATHER_FORECAST_API_KEY = "data/2.5/forecast?&appid=1fb564b0448c20cff8c8a08d408dba5b"
const val WEATHER_METRICS = "&units=metric"
const val WEATHER_LANGUAGES = "&lang=ru"

const val WEATHER_API =
    OPEN_WEATHER_MAP + WEATHER_FORECAST_API_KEY + WEATHER_METRICS + WEATHER_LANGUAGES

interface WeatherApi {
    @GET(WEATHER_API)
    suspend fun getWeather(
        @Query("q") city: String
    ): WeatherResponse
}

object WeatherRetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(OPEN_WEATHER_MAP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}

