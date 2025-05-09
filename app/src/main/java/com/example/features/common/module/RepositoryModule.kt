package com.example.features.common.module

import com.example.features.common.repository.UserRepository
import com.example.features.common.repository.UserRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<UserRepository> { UserRepositoryImpl(get()) }
    }
}