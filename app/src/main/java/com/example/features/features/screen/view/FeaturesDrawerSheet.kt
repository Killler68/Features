package com.example.features.features.screen.view

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.model.FeaturesState
import com.example.features.features.viewmodel.FeaturesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun FeaturesDrawerSheet(state: FeaturesState.Success) {
    val scope = rememberCoroutineScope()
    val viewModel: FeaturesViewModel = getViewModel()
    val sharedViewModel: SharedViewModel = getViewModel()
    val user by sharedViewModel.currentUser.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(state.itemDrawer.getOrNull(0)) }

    state.itemDrawer.forEach { item ->
        TextButton(
            onClick = {
                scope.launch { drawerState.close() }
                selectedItem.value = item
                drawerNavigateTo(item, user?.id, viewModel)
            }
        ) {
            DrawerItemView(item)
        }
    }
}