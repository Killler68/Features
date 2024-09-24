package com.example.features.navigation

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.features.design.DsRegistration

@Composable
fun NavigationAppHost(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "NavigationScreen") {
        composable(Screens.NavigationScreen.route) {
            DsNavigationScreen(navHostController = navHostController)
        }
        composable(Screens.RegistrationFragment.route) { DsRegistration() }
    }
}

@Composable
fun DsNavigationScreen(navHostController: NavHostController) {

    Button(onClick = { navHostController.navigate(Screens.RegistrationFragment.route) }) {

    }
}