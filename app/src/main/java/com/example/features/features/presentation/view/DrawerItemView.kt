package com.example.features.features.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.features.domain.entities.DrawerItem
import com.example.features.features.domain.entities.FeaturesItemDrawer

@Composable
fun DrawerItemView(drawerItem: DrawerItem, login: String) {
        Column(modifier = Modifier.fillMaxWidth(0.6f)) {
        when (drawerItem.id) {
            FeaturesItemDrawer.PROFILE_PREVIEW -> DrawerImagePreview()
            FeaturesItemDrawer.PROFILE -> DrawerUserLoginPreview(login)
            else -> DrawerDefaultItem(drawerItem)
        }
    }
}