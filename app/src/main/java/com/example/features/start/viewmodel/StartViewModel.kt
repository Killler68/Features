package com.example.features.start.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StartViewModel(
    private val checkLocaleUseCase: CheckLocaleUseCase
) : ViewModel() {

    fun checkLocale() = viewModelScope.launch {
        checkLocaleUseCase()
    }
}
