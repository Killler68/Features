package com.example.features.welcome.domain.usecase

import com.example.features.welcome.domain.entities.PagerItems

class WelcomeUseCase(private val repository: WelcomeRepository) {

    operator fun invoke(): List<PagerItems> = repository.getPagerItem()
}