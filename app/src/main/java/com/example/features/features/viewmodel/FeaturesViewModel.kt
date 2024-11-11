package com.example.features.features.viewmodel

import androidx.lifecycle.ViewModel

class FeaturesViewModel(
    private val features: FeaturesUseCase,
) : ViewModel() {

    fun loadFeatures() = features()

}
