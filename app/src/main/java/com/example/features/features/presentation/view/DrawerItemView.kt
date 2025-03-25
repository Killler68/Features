package com.example.features.features.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.features.domain.entities.DrawerItems
import com.example.features.features.domain.entities.FeaturesItemDrawer

@Composable
fun DrawerItemView(drawerItem: DrawerItems, login: String) {
        Column(modifier = Modifier.fillMaxWidth(0.6f)) {
        when (drawerItem.id) {
            FeaturesItemDrawer.PROFILE_PREVIEW -> DrawerImagePreview()
            FeaturesItemDrawer.PROFILE -> DrawerUserLoginPreview(login)
            else -> DrawerDefaultItem(drawerItem)
        }
    }
}