package com.example.features.features.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.model.DrawerItems
import com.example.features.features.model.FeaturesItemDrawer
import org.koin.androidx.compose.getViewModel

@Composable
fun DrawerItemView(drawerItem: DrawerItems) {
    val sharedViewModel: SharedViewModel = getViewModel()
    val login = sharedViewModel.currentUser.collectAsState().value?.login
    Column(modifier = Modifier.fillMaxWidth(0.6f)) {
        when (drawerItem.id) {
            FeaturesItemDrawer.PROFILE_PREVIEW -> DrawerImagePreview(drawerItem.image)
            FeaturesItemDrawer.PROFILE -> login?.let { DrawerUserLoginPreview(it) }
            else -> DrawerDefaultItem(drawerItem)
        }
    }
}