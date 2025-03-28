package com.example.features.features.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.common.view.NotFound
import com.example.features.common.view.ShimmerEffect
import com.example.features.features.presentation.models.FeaturesState


@Composable
fun ItemPagerNotes(state: FeaturesState.Success) {
    if (state.isNotesLoading) ShimmerEffect()
    else if (state.itemNote.isNotEmpty()) ItemPagerNotesView(state)
    else NotFound(nameError = stringResource(R.string.notes_not_found))
}
