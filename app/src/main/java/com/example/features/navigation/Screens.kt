package com.example.features.navigation

sealed class Screens(val route: String) {


    data object Registration : Screens("Registration")
    data object Authorization : Screens("Authorization")
    data object Features : Screens("Features")
    data object Weather : Screens("Weather")
    data object NotesList : Screens("NotesList")
}