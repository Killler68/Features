package com.example.features.features.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.features.model.DrawerItems
import com.example.features.features.model.FeaturesItemDrawer

@Composable
fun DrawerItemView(drawerItem: DrawerItems) {
        Column(modifier = Modifier.fillMaxWidth(0.6f)) {
        when (drawerItem.id) {
            FeaturesItemDrawer.PROFILE_PREVIEW -> DrawerImagePreview()
            FeaturesItemDrawer.PROFILE -> Text(text = "LOGIN")
            else -> DrawerDefaultItem(drawerItem)
        }
    }
}