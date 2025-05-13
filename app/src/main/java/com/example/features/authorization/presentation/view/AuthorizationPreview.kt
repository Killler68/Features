package com.example.features.authorization.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.authorization.presentation.AuthorizationViewModel
import com.example.features.authorization.presentation.models.AuthorizationEvent
import com.example.features.common.view.TextNavigateToView

@Composable
fun AuthorizationPreview(viewModel: AuthorizationViewModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
    )
    Text(
        text = stringResource(R.string.welcome),
        fontSize = 30.sp
    )
    TextNavigateToView(
        title = stringResource(R.string.is_not_account),
        subTitle = stringResource(R.string.register),
        onClick = { viewModel.dispatch(AuthorizationEvent.NavigateToRegistration) }
    )
}