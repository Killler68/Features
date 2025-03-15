package com.example.features.profile.model

sealed class ProfileEvent {
    data object OnClickSettings : ProfileEvent()
    data object OnClickCancel : ProfileEvent()
    data object OnClickApply : ProfileEvent()
    data object ToBack : ProfileEvent()
}