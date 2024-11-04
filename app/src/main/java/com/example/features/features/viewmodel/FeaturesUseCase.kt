package com.example.features.features.viewmodel

import com.example.features.features.model.Features

interface FeaturesUseCase {

    operator fun invoke(): List<Features>
}