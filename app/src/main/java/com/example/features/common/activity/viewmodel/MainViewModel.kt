package com.example.features.common.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.usecase.CheckLocaleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val checkLocaleUseCase: CheckLocaleUseCase
) : ViewModel() {

    private val _locale = MutableStateFlow("")
    val locale: StateFlow<String> get() = _locale

    fun checkLocale() {
        viewModelScope.launch {
            val result = checkLocaleUseCase()
            _locale.value = result
        }
    }
}