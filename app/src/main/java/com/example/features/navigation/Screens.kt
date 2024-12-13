package com.example.features.navigation

sealed class Screens(val route: String) {

    data object Registration : Screens("Registration")
    data object Authorization : Screens("Authorization")

    data object Weather : Screens("Weather")
    data object NotesList : Screens("NotesList")
    data object Features : Screens("Features/{userId}") {
        fun createRoute(userId: Int) = "Features/$userId"
    }
    data object UserAdditionalInfo : Screens("UserAdditionalInfo/{userId}") {
        fun createRoute(userId: Int) = "UserAdditionalInfo/$userId"
    }
    data object NotesDetail : Screens("note_detail/{noteId}") {
        fun createRouter(noteId: Int) = "note_detail/$noteId"
    }
}