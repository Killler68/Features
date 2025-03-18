package com.example.features.features.usecase

import com.example.features.features.model.Features

class FeaturesUseCase(private val repository: FeaturesRepository) {

    operator fun invoke(): List<Features> = repository.getFeatures()
}