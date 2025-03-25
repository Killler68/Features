package com.example.features.weatherdetailed.domain.usecase

import android.content.Context
import com.example.features.R
import com.example.features.weather.domain.entities.WeatherDetailedDay
import com.example.features.weatherdetailed.domain.entities.ItemTemperature
import kotlin.math.abs

class ItemTemperatureUseCase {

    operator fun invoke(
        todayWeather: WeatherDetailedDay?,
        yesterdayWeather: WeatherDetailedDay?,
        tomorrowWeather: WeatherDetailedDay?,
        context: Context
    ): List<ItemTemperature> {
        val items = mutableListOf<ItemTemperature>()

        todayWeather?.let { today ->
            yesterdayWeather?.let { yesterday ->
                items.add(
                    ItemTemperature(
                        yesterday.dt,
                        when {
                            today.temp.toInt() > yesterday.temp.toInt() ->
                                context.getString(
                                    R.string.temp_was_hotter,
                                    abs(today.temp.toInt() - yesterday.temp.toInt())
                                )

                            today.temp.toInt() == yesterday.temp.toInt() ->
                                context.getString(R.string.temp_same)

                            else ->
                                context.getString(
                                    R.string.temp_was_colder,
                                    abs(today.temp.toInt() - yesterday.temp.toInt())
                                )
                        }
                    )
                )
            }

            items.add(
                ItemTemperature(
                    today.dt,
                    context.getString(R.string.weather_today, today.description, today.temp.toInt())
                )
            )

            tomorrowWeather?.let { tomorrow ->
                items.add(
                    ItemTemperature(
                        tomorrow.dt,
                        when {
                            today.temp.toInt() > tomorrow.temp.toInt() ->
                                context.getString(
                                    R.string.temp_will_be_hotter,
                                    abs(today.temp.toInt() - tomorrow.temp.toInt())
                                )

                            today.temp.toInt() == tomorrow.temp.toInt() ->
                                context.getString(R.string.temp_expected_same)

                            else ->
                                context.getString(
                                    R.string.temp_will_be_colder,
                                    abs(today.temp.toInt() - tomorrow.temp.toInt())
                                )
                        }
                    )
                )
            }
        }
        return items
    }
}