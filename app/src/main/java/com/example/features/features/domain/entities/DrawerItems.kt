package com.example.features.features.domain.entities

data class DrawerItems(
    val id: FeaturesItemDrawer,
    val userLogin: String? = "",
    val title: Int,
    val image: Int
)