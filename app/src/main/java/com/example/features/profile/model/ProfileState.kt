package com.example.features.profile.model

import com.example.features.common.database.profile.model.Profile

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