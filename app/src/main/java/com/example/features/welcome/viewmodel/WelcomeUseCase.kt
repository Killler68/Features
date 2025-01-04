package com.example.features.welcome.viewmodel

import com.example.features.welcome.models.PagerItems

interface WelcomeUseCase {

    operator fun invoke(): List<PagerItems>
}