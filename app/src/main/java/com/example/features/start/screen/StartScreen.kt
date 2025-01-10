package com.example.features.start.screen

import androidx.compose.runtime.Composable
import com.example.features.start.viewmodel.StartViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun StartScreen() {

    val viewModel: StartViewModel = getViewModel()

    viewModel.checkLocale()
}