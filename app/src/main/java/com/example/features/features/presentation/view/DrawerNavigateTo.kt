package com.example.features.features.presentation.view

import com.example.features.features.domain.entities.DrawerItems
import com.example.features.features.presentation.models.FeaturesEvent
import com.example.features.features.domain.entities.FeaturesItemDrawer
import com.example.features.features.presentation.FeaturesViewModel

fun drawerNavigateTo(drawerItem: DrawerItems, userId: Int?, viewModel: FeaturesViewModel) {
    when (drawerItem.id) {
        FeaturesItemDrawer.PROFILE_PREVIEW, FeaturesItemDrawer.PROFILE -> userId?.let {
            viewModel.dispatch(FeaturesEvent.NavigateToProfile(it))
        }

        FeaturesItemDrawer.SETTINGS -> userId?.let {
            FeaturesEvent.NavigateToSettings(
                it
            )
        }?.let { viewModel.dispatch(it) }

        FeaturesItemDrawer.ABOUT -> viewModel.dispatch(FeaturesEvent.NavigateToAbout)
    }
}