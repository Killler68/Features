package com.example.features.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.features.common.viewmodel.CommonViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider


@Module
class ViewModelModule {
    @Provides
    fun provideViewModelFactory(
        viewModelProviders: MutableMap<Class<*>, Provider<ViewModel>>
    ): ViewModelProvider.Factory = CommonViewModelFactory(viewModelProviders)

}