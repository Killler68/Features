package com.example.features.features.model

sealed class FeaturesEvent {
    data class LoadAllData(val userId: Int) : FeaturesEvent()
    data object NavigateToAbout : FeaturesEvent()
    data class NavigateToFeature(val featureId: String) : FeaturesEvent()
    data class NavigateToProfile(val userId: Int) : FeaturesEvent()
    data class NavigateToSettings(val userId: Int) : FeaturesEvent()
}