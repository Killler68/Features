package com.example.features.authorization

import com.example.features.authorization.usecase.GetUserByLoginAndPasswordUseCase
import com.example.features.authorization.viewmodel.AuthorizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AuthorizationModule {
    val module = module {
        factory { GetUserByLoginAndPasswordUseCase(get()) }
        viewModel { AuthorizationViewModel(get()) }
    }
}