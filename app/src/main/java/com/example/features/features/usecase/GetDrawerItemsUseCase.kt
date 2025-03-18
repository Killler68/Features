package com.example.features.features.usecase

import com.example.features.features.model.DrawerItems

class GetDrawerItemsUseCase(private val repository: FeaturesRepository) {

    operator fun invoke(): List<DrawerItems> = repository.getDrawerItems()
}