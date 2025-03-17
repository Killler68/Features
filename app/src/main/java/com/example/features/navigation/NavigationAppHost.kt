package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.features.about.AboutScreen
import com.example.features.authorization.DsAuthorization
import com.example.features.features.screen.FeaturesScreen
import com.example.features.notes.noteadd.screen.NoteAddScreen
import com.example.features.notes.notedetail.screen.DsNoteDetail
import com.example.features.notes.noteslist.screen.NotesListScreen
import com.example.features.notes.task.screen.TaskScreen
import com.example.features.profile.screen.ProfileScreen
import com.example.features.registration.DsRegistration
import com.example.features.settings.screen.SettingsScreen
import com.example.features.weather.detailed.screen.WeatherDetailedScreen
import com.example.features.weather.screen.WeatherScreen
import com.example.features.welcome.screen.WelcomeScreen

@Composable
fun NavigationAppHost(checkLocale: String) {
    val navHostController = rememberNavController()

    if (checkLocale.isNotEmpty()) {
        NavHost(navController = navHostController, startDestination = checkLocale) {
            composable(Screens.Welcome.route) { WelcomeScreen(navHostController) }
            composable(Screens.Registration.route) { DsRegistration(navHostController) }
            composable(Screens.Authorization.route) { DsAuthorization(navHostController) }
            composable(Screens.Weather.route) { WeatherScreen(navHostController) }
            composable(Screens.NotesList.route) { NotesListScreen(navHostController) }
            composable(Screens.Features.route) { FeaturesScreen(navHostController) }
            composable(Screens.NoteAddScreen.route) { NoteAddScreen(navHostController) }
            composable(Screens.TaskScreen.route) { TaskScreen(navHostController) }
            composable(Screens.SettingsScreen.route) { SettingsScreen(navHostController) }
            composable(Screens.AboutScreen.route) { AboutScreen(navHostController) }

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
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                DsNoteDetail(noteId = noteId, navHostController)
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