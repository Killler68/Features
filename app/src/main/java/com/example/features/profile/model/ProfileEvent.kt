package com.example.features.profile.model

sealed class ProfileEvent {
    data object OnClickSettings : ProfileEvent()
    data object OnClickCancel : ProfileEvent()
    data object OnClickApply : ProfileEvent()
    data class OnClickBack(val userId: Int) : ProfileEvent()
    data object OnClickExit : ProfileEvent()
    data class OnNameChange(val value: String) : ProfileEvent()
    data class OnAgeChange(val value: String) : ProfileEvent()
    data class OnCityChange(val value: String) : ProfileEvent()
    data class OnNationalityChange(val value: String) : ProfileEvent()
    data class OnEmailChange(val value: String) : ProfileEvent()
    data class LoadProfile(val userId: Int) : ProfileEvent()
}