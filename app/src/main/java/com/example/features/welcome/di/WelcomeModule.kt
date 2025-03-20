package com.example.features.welcome.di

import com.example.features.welcome.data.WelcomeRepositoryImpl
import com.example.features.welcome.domain.usecase.WelcomeRepository
import com.example.features.welcome.domain.usecase.WelcomeUseCase
import com.example.features.welcome.presentation.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WelcomeModule {
    val module = module {
        single<WelcomeRepository> { WelcomeRepositoryImpl() }
        factory { WelcomeUseCase(get()) }
        viewModel { WelcomeViewModel(get()) }
    }
}