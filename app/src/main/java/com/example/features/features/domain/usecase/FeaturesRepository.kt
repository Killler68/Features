package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.DrawerItems
import com.example.features.features.domain.entities.Features

interface FeaturesRepository {

    fun getFeatures(): List<Features>
   suspend fun getDrawerItems(userId: Int): List<DrawerItems>
}