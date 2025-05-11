package com.example.features.features.di

import com.example.features.features.data.FeaturesRepositoryImpl
import com.example.features.features.domain.usecase.FeaturesRepository
import com.example.features.features.domain.usecase.GetFeaturesUseCase
import com.example.features.features.domain.usecase.GetDrawerItemUseCase
import com.example.features.features.presentation.FeaturesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object FeaturesModule {
    val module = module {
        single<FeaturesRepository> { FeaturesRepositoryImpl(get()) }
        factory { GetFeaturesUseCase(get()) }
        factory { GetDrawerItemUseCase(get()) } //todo use factoryOf(::GetDrawerItemUseCase)
        viewModel { FeaturesViewModel(get(), get(), get(), get(), get()) } //todo use viewModelOf(::FeaturesViewModel) (need to implement koin view model for it)
    }
}