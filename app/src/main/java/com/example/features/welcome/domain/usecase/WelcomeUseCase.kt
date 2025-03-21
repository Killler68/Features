package com.example.features.welcome.domain.usecase

import com.example.features.welcome.domain.entities.PagerItem

class WelcomeUseCase(private val repository: WelcomeRepository) {

    operator fun invoke(): List<PagerItem> = repository.getPagerItem()
}