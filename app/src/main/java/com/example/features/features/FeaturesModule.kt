package com.example.features.features

import com.example.features.features.repository.FeaturesRepositoryImpl
import com.example.features.features.usecase.FeaturesRepository
import com.example.features.features.usecase.FeaturesUseCase
import com.example.features.features.usecase.GetDrawerItemsUseCase
import com.example.features.features.viewmodel.FeaturesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FeaturesModule {
    val module = module {
        single<FeaturesRepository> { FeaturesRepositoryImpl() }
        factory { FeaturesUseCase(get()) }
        factory { GetDrawerItemsUseCase(get()) }
        viewModel { FeaturesViewModel(get(), get(), get(), get(), get()) }
    }
}