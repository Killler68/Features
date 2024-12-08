package com.example.features.features.usecase

import com.example.features.features.model.DrawerItems
import com.example.features.features.viewmodel.GetDrawerItems

class GetDrawerItemsImpl(
    private val repository: FeaturesRepository
) : GetDrawerItems {

    override fun invoke(): List<DrawerItems> = repository.getDrawerItems()
}