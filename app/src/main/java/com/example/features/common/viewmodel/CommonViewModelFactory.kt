package com.example.features.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider


class CommonViewModelFactory (
    private val viewModelProviders: Map<Class<*>, Provider<ViewModel>>
        ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModelProviders[modelClass]?.get() as? T
            ?: throw ClassNotFoundException("Зависимость ${modelClass.simpleName} не зарегистрирована")
        }