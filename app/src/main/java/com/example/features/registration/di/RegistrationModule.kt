package com.example.features.registration.di

import com.example.features.registration.domain.CreateUserUseCase
import com.example.features.registration.domain.GetUserByLoginUseCase
import com.example.features.registration.presentation.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RegistrationModule {
    val module = module {
        factory { CreateUserUseCase(get()) }
        factory { GetUserByLoginUseCase(get()) }
        viewModel { RegistrationViewModel(get(), get(), get()) }
    }
}