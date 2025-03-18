package com.example.features.common.application

import com.example.features.common.viewmodel.SharedViewModel
import org.koin.dsl.module

object SharedModule {
    val module = module {
        single { SharedViewModel(get()) }
    }
}