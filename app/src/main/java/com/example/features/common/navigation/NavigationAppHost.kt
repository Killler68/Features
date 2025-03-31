package com.example.features.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.features.about.AboutScreen
import com.example.features.authorization.presentation.AuthorizationScreen
import com.example.features.features.presentation.FeaturesScreen
import com.example.features.notes.presentation.view.NoteAddScreen
import com.example.features.notes.presentation.view.NoteDetailedScreen
import com.example.features.notes.presentation.view.NotesTaskScreen
import com.example.features.notes.presentation.view.TaskAddScreen
import com.example.features.profile.presentation.ProfileScreen
import com.example.features.registration.presentation.RegistrationScreen
import com.example.features.settings.presentation.SettingsScreen
import com.example.features.weather.presentation.WeatherScreen
import com.example.features.weatherdetailed.presentation.WeatherDetailedScreen
import com.example.features.welcome.presentation.WelcomeScreen

@Composable
fun NavigationAppHost(checkLocale: String) {
    val navHostController = rememberNavController()

    if (checkLocale.isNotEmpty()) {
        NavHost(navController = navHostController, startDestination = checkLocale) {
            composable(Screens.Welcome.route) { WelcomeScreen(navHostController) }
            composable(Screens.Registration.route) { RegistrationScreen(navHostController) }
            composable(Screens.Authorization.route) { AuthorizationScreen(navHostController) }
            composable(Screens.AboutScreen.route) { AboutScreen(navHostController) }
            composable(Screens.Weather.route) { WeatherScreen(navHostController) }

            composable(
                Screens.SettingsScreen.route,
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                SettingsScreen(navHostController, userId)
            }

            composable(
                Screens.NoteAddScreen.route,
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                NoteAddScreen(userId, navHostController)
            }

            composable(
                Screens.TaskAddScreen.route,
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                TaskAddScreen(userId, navHostController)
            }

            composable(
                Screens.NotesTaskScreen.route,
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                NotesTaskScreen(userId, navHostController)
            }

            composable(
                route = Screens.Features.route,
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                FeaturesScreen(navHostController, userId)
            }
            composable(
                route = Screens.Profile.route,
                arguments = listOf(navArgument("userId") {
                    type = NavType.IntType; defaultValue = -1
                })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                ProfileScreen(userId, navHostController)
            }

            composable(
                route = Screens.NotesDetail.route,
                arguments = listOf(
                    navArgument("userId") { type = NavType.IntType },
                    navArgument("noteId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailedScreen(userId = userId, noteId = noteId, navController = navHostController)
            }

            composable(
                route = Screens.WeatherDetailedScreen.route,
                arguments = listOf(navArgument("weatherId") { type = NavType.IntType })
            ) { backStackEntry ->
                val weatherId = backStackEntry.arguments?.getInt("weatherId") ?: 0
                WeatherDetailedScreen(weatherId, navHostController)
            }
        }
    }
}