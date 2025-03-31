package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.Features

class GetFeaturesUseCase(private val repository: FeaturesRepository) {

    operator fun invoke(userId: Int): List<Features> = repository.getFeatures(userId)
}