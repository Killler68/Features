package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.features.MainViewModel
import com.example.features.authorization.DsAuthorization
import com.example.features.common.application.MainApp
import com.example.features.features.DsFeatures
import com.example.features.design.DsWeather
import com.example.features.registration.DsRegistration
import com.example.features.weather.viewmodel.WeatherViewModel

@Composable
fun NavigationAppHost(mainViewModel: MainViewModel, weatherViewModel: WeatherViewModel) {
    val navHostController = rememberNavController()



    NavHost(navController = navHostController, startDestination = "Registration") {
        composable(Screens.Registration.route) {
            DsRegistration(navHostController, mainViewModel)
        }
        composable(Screens.Authorization.route) {
            DsAuthorization(
                navHostController,
                mainViewModel
            )
        }
        composable(Screens.Features.route) { DsFeatures(navHostController, mainViewModel) }
        composable(Screens.Weather.route) { DsWeather(weatherViewModel, navHostController) }
    }
}