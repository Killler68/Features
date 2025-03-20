package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.DrawerItems

class GetDrawerItemsUseCase(private val repository: FeaturesRepository) {

    operator fun invoke(): List<DrawerItems> = repository.getDrawerItems()
}