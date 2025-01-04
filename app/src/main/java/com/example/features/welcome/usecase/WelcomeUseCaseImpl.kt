package com.example.features.welcome.usecase

import com.example.features.welcome.models.PagerItems
import com.example.features.welcome.viewmodel.WelcomeUseCase

class WelcomeUseCaseImpl(
    private val repository: WelcomeRepository
) : WelcomeUseCase {

    override fun invoke(): List<PagerItems> = repository.getPagerItem()
}