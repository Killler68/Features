package com.example.features.profile.domain.entities

sealed class Option {
    data class Enabled(val isEnabled: Boolean) : Option()
    data class Off(val isEnabled: Boolean) : Option()
}