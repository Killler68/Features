package com.example.features.profile.model

sealed class Option {
    data class Enabled(val isEnabled: Boolean) : Option()
    data class Off(val isEnabled: Boolean) : Option()
}