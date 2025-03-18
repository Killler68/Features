package com.example.features.common.api

import WeatherResponse
import com.example.features.common.strings.OPEN_WEATHER_MAP
import com.example.features.common.strings.WEATHER_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

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