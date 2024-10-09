package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.features.authorization.DsAuthorization
import com.example.features.weather.design.DsWeather
import com.example.features.features.DsFeatures
import com.example.features.registration.DsRegistration

@Composable
fun NavigationAppHost() {
    val navHostController = rememberNavController()


    NavHost(navController = navHostController, startDestination = "Registration") {
        composable(Screens.Registration.route) {
            DsRegistration(navHostController)
        }
        composable(Screens.Authorization.route) {
            DsAuthorization(navHostController)
        }
        composable(Screens.Features.route) { DsFeatures(navHostController) }
        composable(Screens.Weather.route) { DsWeather(navHostController) }
    }
}