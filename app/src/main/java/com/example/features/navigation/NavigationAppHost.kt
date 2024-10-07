package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.features.design.DsAuthorization
import com.example.features.design.DsFeatures
import com.example.features.design.DsRegistration
import com.example.features.design.DsWeather
import com.example.features.weather.viewmodel.WeatherViewModel

@Composable
fun NavigationAppHost(navHostController: NavHostController, viewModel: WeatherViewModel) {

    NavHost(navController = navHostController, startDestination = "RegistrationFragment") {
        composable(Screens.RegistrationFragment.route) {
            DsRegistration(navHostController)
        }
//        composable(Screens.RegistrationFragment.route) { DsRegistration(navHostController) }
        composable(Screens.AuthorizationFragment.route) { DsAuthorization(navHostController) }
        composable(Screens.FeaturesFragment.route) { DsFeatures(navHostController) }
        composable(Screens.WeatherFragment.route) { DsWeather(viewModel, navHostController) }
    }
}