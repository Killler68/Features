package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.features.authorization.DsAuthorization
import com.example.features.features.design.DsFeatures
import com.example.features.notes.design.DsNotesList
import com.example.features.notes.detail.DsNoteDetail
import com.example.features.registration.DsRegistration
import com.example.features.weather.design.DsWeather

@Composable
fun NavigationAppHost() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = "Registration") {
        composable(Screens.Registration.route) { DsRegistration(navHostController) }
        composable(Screens.Authorization.route) { DsAuthorization(navHostController) }
        composable(Screens.Weather.route) { DsWeather(navHostController) }
        composable(Screens.NotesList.route) { DsNotesList(navHostController) }
        composable(Screens.Features.route) { DsFeatures(navHostController) }

        composable(
            route = Screens.NotesDetail.route,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            DsNoteDetail(noteId = noteId, navHostController)
        }
    }
}