package com.example.features.navigation

sealed class Screens(val route: String) {

    data object NavigationScreen : Screens("NavigationScreen")
    data object RegistrationFragment : Screens("RegistrationFragment")
    data object AuthorizationFragment : Screens("AuthorizationFragment")
    data object FeaturesFragment : Screens("FeaturesFragment")
}