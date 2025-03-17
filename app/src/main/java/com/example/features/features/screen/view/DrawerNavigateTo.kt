package com.example.features.features.screen.view

import com.example.features.features.model.DrawerItems
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesItemDrawer
import com.example.features.features.viewmodel.FeaturesViewModel

fun drawerNavigateTo(drawerItem: DrawerItems, userId: Int?, viewModel: FeaturesViewModel) {
    when (drawerItem.id) {
        FeaturesItemDrawer.PROFILE_PREVIEW, FeaturesItemDrawer.PROFILE -> userId?.let {
            viewModel.dispatch(FeaturesEvent.NavigateToProfile(it))
        }

        FeaturesItemDrawer.SETTINGS -> viewModel.dispatch(FeaturesEvent.NavigateToSettings)
        FeaturesItemDrawer.ABOUT -> viewModel.dispatch(FeaturesEvent.NavigateToAbout)
    }
}