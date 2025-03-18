package com.example.features.weather.detailed

import com.example.features.weather.detailed.usecase.ItemTemperatureUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.detailed.usecase.WeatherDetailedUseCase
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import com.example.features.weather.detailed.viewmodel.WeatherDetailedViewModel
import com.example.features.weather.repository.WeatherRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WeatherDetailedModule {
    val module = module {
        single<WeatherDetailedRepository> { WeatherRepositoryImpl() }
        factory { ItemTemperatureUseCase() }
        factory { WeatherDetailedUseCase(get(), get(), get()) }
        factory { WeatherHoursDayUseCase(get()) }
        viewModel { WeatherDetailedViewModel(get()) }
    }
}