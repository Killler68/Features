package com.example.features.weatherdetailed.domain.usecase

import com.example.features.weatherdetailed.domain.entities.ItemTemperature
import com.example.features.weather.domain.entities.WeatherDetailedDay
import kotlin.math.abs

class ItemTemperatureUseCase {

    operator fun invoke(
        todayWeather: WeatherDetailedDay?,
        yesterdayWeather: WeatherDetailedDay?,
        tomorrowWeather: WeatherDetailedDay?
    ): List<ItemTemperature> {
        val items = mutableListOf<ItemTemperature>()

        todayWeather?.let { today ->
            yesterdayWeather?.let { yesterday ->
                items.add(
                    ItemTemperature(
                        yesterday.dt,
                        if (today.temp.toInt() > yesterday.temp.toInt()) {
                            "Было на ${abs(today.temp.toInt() - yesterday.temp.toInt())}° теплее"
                        } else if (today.temp.toInt() == yesterday.temp.toInt()) {
                            "Температура такая же"
                        } else {
                            "Было на ${abs(today.temp.toInt() - yesterday.temp.toInt())}° прохладнее"
                        }

                    )
                )
            }

            items.add(
                ItemTemperature(
                    today.dt,
                    "${today.description} и температура воздуха ${today.temp.toInt()}°"
                )
            )

            tomorrowWeather?.let { tomorrow ->
                items.add(
                    ItemTemperature(
                        tomorrow.dt,
                        if (today.temp.toInt() > tomorrow.temp.toInt()) {
                            "Будет на ${abs(today.temp.toInt() - tomorrow.temp.toInt())}° теплее"
                        } else if (today.temp.toInt() == tomorrow.temp.toInt()) {
                            "Ожидается такая же температура"
                        } else {
                            "Будет на ${abs(today.temp.toInt() - tomorrow.temp.toInt())}° прохладнее"
                        }
                    )
                )
            }
        }
        return items
    }
}