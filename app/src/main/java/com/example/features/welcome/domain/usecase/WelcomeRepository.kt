package com.example.features.welcome.domain.usecase

import com.example.features.welcome.domain.entities.PagerItems

interface WelcomeRepository {

    fun getPagerItem(): List<PagerItems>
}