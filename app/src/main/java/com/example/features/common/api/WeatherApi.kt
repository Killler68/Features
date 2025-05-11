package com.example.features.common.api

import com.example.features.weather.domain.entities.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val domain = "https://api.openweathermap.org/" //naming and private
private const val FORECAST_API_KEY = "data/2.5/forecast?&appid=1fb564b0448c20cff8c8a08d408dba5b" //move to build config
private const val METRICS = "&units=metric"
private const val LANGUAGES = "&lang=ru"

const val WEATHER_API =
    domain + WEATHER_FORECAST_API_KEY + WEATHER_METRICS + WEATHER_LANGUAGES

interface WeatherApi {
    @GET(WEATHER_API)
    suspend fun getWeather(
        @Query("q") city: String
    ): WeatherResponse
}

object WeatherRetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(domain)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}

