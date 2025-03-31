package com.example.features.common.navigation

sealed class Screens(val route: String) {

    data object Welcome : Screens("Welcome")
    data object Registration : Screens("Registration")
    data object Authorization : Screens("Authorization")

    data object Weather : Screens("Weather")
    data object WeatherDetailedScreen : Screens("weather_detailed_screen/{weatherId}") {
        fun createRouter(weatherId: String) = "weather_detailed_screen/$weatherId"
    }

    data object Features : Screens("Features/{userId}") {
        fun createRoute(userId: Int) = "Features/$userId"
    }

    data object NoteAddScreen : Screens("note_add_screen/{userId}") {
        fun createRoute(userId: Int) = "note_add_screen/$userId"
    }

    data object TaskAddScreen : Screens("task_add_screen/{userId}") {
        fun createRoute(userId: Int) = "task_add_screen/$userId"
    }

    data object NotesTaskScreen : Screens("notes_task_screen/{userId}") {
        fun createRoute(userId: Int) = "notes_task_screen/$userId"
    }

    data object Profile : Screens("Profile/{userId}") {
        fun createRoute(userId: Int) = "Profile/$userId"
    }

    data object NotesDetail : Screens("note_detail/{userId}/{noteId}") {
        fun createRouter(userId: Int, noteId: Int) = "note_detail/$userId/$noteId"
    }

    data object SettingsScreen : Screens("settings_screen/{userId}") {
        fun createRouter(userId: Int) = "settings_screen/$userId"
    }

    data object AboutScreen : Screens("about_screen")
}