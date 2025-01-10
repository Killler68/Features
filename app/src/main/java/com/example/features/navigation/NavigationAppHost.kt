package com.example.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.features.authorization.DsAuthorization
import com.example.features.features.design.DsFeatures
import com.example.features.notes.noteslist.screen.DsNotesList
import com.example.features.notes.notedetail.screen.DsNoteDetail
import com.example.features.notes.noteadd.screen.NoteAddScreen
import com.example.features.profile.DsUserAdditionalInfo
import com.example.features.registration.DsRegistration
import com.example.features.weather.design.DsWeather
import com.example.features.welcome.DsWelcome

@Composable
fun NavigationAppHost() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = "Welcome") {
        composable(Screens.Welcome.route) { DsWelcome(navHostController) }
        composable(Screens.Registration.route) { DsRegistration(navHostController) }
        composable(Screens.Authorization.route) { DsAuthorization(navHostController) }
        composable(Screens.Weather.route) { DsWeather(navHostController) }
        composable(Screens.NotesList.route) { DsNotesList(navHostController) }
        composable(Screens.Features.route) { DsFeatures(navHostController) }
        composable(Screens.NoteAddScreen.route) { NoteAddScreen(navHostController) }

        composable(
            route = Screens.UserAdditionalInfo.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType; defaultValue = -1 })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1
            DsUserAdditionalInfo(navHostController, userId)
        }

        composable(
            route = Screens.NotesDetail.route,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            DsNoteDetail(noteId = noteId, navHostController)
        }
    }
}