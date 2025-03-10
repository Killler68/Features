package com.example.features.features.screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesItemDrawer
import com.example.features.features.model.FeaturesState
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.ui.theme.LightGray
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@Composable
fun FeaturesDrawerSheet(state: FeaturesState.Success) {
    val scope = rememberCoroutineScope()

    val items = state.itemDrawer
    val selectedItem = remember { mutableStateOf(items.getOrNull(0)) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val viewModel: FeaturesViewModel = getViewModel()

    val sharedViewModel: SharedViewModel = getViewModel()
    val user by sharedViewModel.currentUser.collectAsState()

    items.forEach { item ->
        TextButton(
            onClick = {
                scope.launch { drawerState.close() }
                selectedItem.value = item

                when (item.id) {
                    FeaturesItemDrawer.PROFILE_PREVIEW, FeaturesItemDrawer.PROFILE -> user?.id?.let {
                        viewModel.dispatch(FeaturesEvent.NavigateToProfile(it))
                    }

                    FeaturesItemDrawer.SETTINGS -> viewModel.dispatch(FeaturesEvent.NavigateToSettings)
                    FeaturesItemDrawer.ABOUT -> viewModel.dispatch(FeaturesEvent.NavigateToAbout)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                when (item.id) {
                    FeaturesItemDrawer.PROFILE_PREVIEW -> {
                        Image(
                            painter = painterResource(item.image),
                            contentDescription = "profile",
                            colorFilter = ColorFilter.tint(LightGray),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                                .size(180.dp),
                            alignment = Alignment.Center
                        )
                    }

                    FeaturesItemDrawer.PROFILE -> {
                        user?.login?.let {
                            Text(
                                text = it,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }

                    else -> {
                        Row {
                            Image(
                                painter = painterResource(item.image),
                                contentDescription = "drawer",
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(LightGray)
                                    .padding(7.dp)
                            )
                            Text(
                                text = item.title,
                                fontSize = 16.sp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 5.dp)

                            )
                        }
                    }
                }
            }
        }
    }
}