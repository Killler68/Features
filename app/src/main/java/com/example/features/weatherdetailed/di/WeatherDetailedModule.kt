package com.example.features.weatherdetailed.di

import com.example.features.weatherdetailed.data.WeatherDetailedRepositoryImpl
import com.example.features.weatherdetailed.domain.usecase.ItemTemperatureUseCase
import com.example.features.weatherdetailed.domain.usecase.WeatherDetailedRepository
import com.example.features.weatherdetailed.domain.usecase.WeatherDetailedUseCase
import com.example.features.weatherdetailed.domain.usecase.WeatherHoursDayUseCase
import com.example.features.weatherdetailed.presentation.WeatherDetailedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WeatherDetailedModule {
    val module = module {
        single<WeatherDetailedRepository> { WeatherDetailedRepositoryImpl() }

        factory { ItemTemperatureUseCase() }
        factory { WeatherDetailedUseCase(get(), get(), get()) }
        factory { WeatherHoursDayUseCase(get()) }

        viewModel { WeatherDetailedViewModel(get()) }
    }
}