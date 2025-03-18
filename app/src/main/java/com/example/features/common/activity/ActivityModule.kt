package com.example.features.common.activity

import com.example.features.common.activity.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ActivityModule {
    val module = module {
        viewModel { MainViewModel(get()) }
    }
}