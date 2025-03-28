package com.example.features.welcome.domain.usecase

import com.example.features.welcome.domain.entities.PagerItem

interface WelcomeRepository {

    fun getPagerItem(): List<PagerItem>
}