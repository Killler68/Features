package com.example.features.authorization.di

import com.example.features.authorization.domain.GetUserByLoginAndPasswordUseCase
import com.example.features.authorization.presentation.AuthorizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AuthorizationModule {
    val module = module {
        factory { GetUserByLoginAndPasswordUseCase(get()) }
        viewModel { AuthorizationViewModel(get()) }
    }
}