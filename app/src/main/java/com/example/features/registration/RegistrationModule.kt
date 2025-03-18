package com.example.features.registration

import com.example.features.registration.usecase.CreateUserUseCase
import com.example.features.registration.usecase.GetUserByLoginUseCase
import com.example.features.registration.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RegistrationModule {
    val module = module {
        factory { CreateUserUseCase(get()) }
        factory { GetUserByLoginUseCase(get()) }
        viewModel { RegistrationViewModel(get(), get(), get()) }
    }
}