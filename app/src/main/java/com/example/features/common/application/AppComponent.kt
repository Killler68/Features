package com.example.features.common.application

import androidx.lifecycle.ViewModelProvider
import com.example.features.common.viewmodel.ViewModelModule
import com.example.features.common.FeaturesModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        FeaturesModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun getViewModelFactory(): ViewModelProvider.Factory
}