package com.example.features.navigation

sealed class Screens(val route: String) {


    data object Registration : Screens("Registration")
    data object Authorization : Screens("Authorization")
    data object Features : Screens("Features")
    data object Weather : Screens("Weather")
    data object NotesList : Screens("NotesList")
    data object Profile: Screens("Profile")
    data object NotesDetail : Screens("note_detail/{noteId}") {
        fun createRouter(noteId: Int) = "note_detail/$noteId"
    }
}