package com.example.features.profile.presentation.models

import com.example.features.profile.data.database.model.Profile
import com.example.features.profile.domain.entities.Option

data class ProfileState(
    val profile: Profile? = null,
    val isOption: Option = Option.Off(false),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val editName: String = "",
    val editAge: String = "",
    val editCity: String = "",
    val editNationality: String = "",
    val editEmail: String = ""
)