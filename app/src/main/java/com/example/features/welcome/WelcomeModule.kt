package com.example.features.welcome

import com.example.features.welcome.repository.WelcomeRepositoryImpl
import com.example.features.welcome.usecase.WelcomeRepository
import com.example.features.welcome.usecase.WelcomeUseCase
import com.example.features.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WelcomeModule {
    val module = module {
        single<WelcomeRepository> { WelcomeRepositoryImpl() }
        factory { WelcomeUseCase(get()) }
        viewModel { WelcomeViewModel(get()) }
    }
}