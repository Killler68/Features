package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.Features

class FeaturesUseCase(private val repository: FeaturesRepository) {

    operator fun invoke(): List<Features> = repository.getFeatures()
}