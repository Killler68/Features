package com.example.features.welcome.usecase

import com.example.features.welcome.models.PagerItems

class WelcomeUseCase(private val repository: WelcomeRepository) {

    operator fun invoke(): List<PagerItems> = repository.getPagerItem()
}