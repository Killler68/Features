package com.example.features.features.di

import com.example.features.features.data.FeaturesRepositoryImpl
import com.example.features.features.domain.usecase.FeaturesRepository
import com.example.features.features.domain.usecase.FeaturesUseCase
import com.example.features.features.domain.usecase.GetDrawerItemUseCase
import com.example.features.features.presentation.FeaturesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FeaturesModule {
    val module = module {
        single<FeaturesRepository> { FeaturesRepositoryImpl(get()) }
        factory { FeaturesUseCase(get()) }
        factory { GetDrawerItemUseCase(get()) }
        viewModel { FeaturesViewModel(get(), get(), get(), get(), get()) }
    }
}