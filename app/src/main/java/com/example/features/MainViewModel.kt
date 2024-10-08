package com.example.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<MainScreenState>()
    val state: LiveData<MainScreenState> = _state

    init {
        _state.value = MainScreenState.Registration
    }

    fun handleEvent(event: MainScreenEvent) {

        when (event) {
            is MainScreenEvent.NavigateToRegistration -> _state.value =
                MainScreenState.Registration

            is MainScreenEvent.NavigateToAuthorization -> _state.value =
                MainScreenState.Authorization

            is MainScreenEvent.NavigateToFeatures -> _state.value =
                MainScreenState.Features

            is MainScreenEvent.NavigateToWeather -> _state.value =
                MainScreenState.Weather
        }
    }
}