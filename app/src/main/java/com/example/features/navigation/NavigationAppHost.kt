package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.features.design.DsAuthorization
import com.example.features.design.DsRegistration

@Composable
fun NavigationAppHost(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "RegistrationFragment") {
        composable(Screens.RegistrationFragment.route) {
            DsRegistration(navHostController)
        }
//        composable(Screens.RegistrationFragment.route) { DsRegistration(navHostController) }
        composable(Screens.AuthorizationFragment.route) { DsAuthorization(navHostController) }
    }
}