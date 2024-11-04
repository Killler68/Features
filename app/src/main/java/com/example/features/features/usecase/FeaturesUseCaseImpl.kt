package com.example.features.features.usecase

import com.example.features.features.model.Features
import com.example.features.features.viewmodel.FeaturesUseCase

class FeaturesUseCaseImpl(
    private val repository: FeaturesRepository
) : FeaturesUseCase {

    override fun invoke(): List<Features> = repository.getFeatures()
}