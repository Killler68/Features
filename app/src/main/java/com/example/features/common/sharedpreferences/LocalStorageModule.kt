package com.example.features.common.sharedpreferences

import com.example.features.common.usecase.CheckLocaleUseCase
import com.example.features.common.usecase.CheckLocaleUseCaseImpl
import org.koin.dsl.module

object LocalStorageModule {
    val module = module {
        single<CheckLocaleUseCase> { CheckLocaleUseCaseImpl(get()) }
        factory<LocalStorage> { LocalStorageImpl(get()) }
    }
}