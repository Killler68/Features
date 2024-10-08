package com.example.features

sealed class MainScreenState {
    data object Registration : MainScreenState()
    data object Authorization : MainScreenState()
    data object Features : MainScreenState()
    data object Weather : MainScreenState()
}

sealed class MainScreenEvent() {
    data object NavigateToRegistration : MainScreenEvent()
    data object NavigateToAuthorization : MainScreenEvent()
    data object NavigateToFeatures : MainScreenEvent()
    data object NavigateToWeather : MainScreenEvent()
}
