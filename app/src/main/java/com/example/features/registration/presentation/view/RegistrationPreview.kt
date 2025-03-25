package com.example.features.registration.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.view.TextNavigateToView
import com.example.features.registration.presentation.RegistrationViewModel
import com.example.features.registration.presentation.models.RegistrationEvent
import org.koin.androidx.compose.getViewModel


@Composable
fun RegistrationPreview() {
    val viewModel: RegistrationViewModel = getViewModel()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
    )
    Text(stringResource(R.string.registration), fontSize = 30.sp)
    TextNavigateToView(
        title = stringResource(R.string.is_registered),
        subTitle = stringResource(R.string.log_in),
        onClick = { viewModel.dispatch(RegistrationEvent.NavigateToAuthorization) }
    )
}
