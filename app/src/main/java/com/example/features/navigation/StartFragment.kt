package com.example.features.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.features.MainViewModel
import com.example.features.common.fragment.getViewModelFactory
import com.example.features.registration.DsRegistration
import com.example.features.weather.viewmodel.WeatherViewModel

class StartFragment : Fragment() {


    private val weatherViewModel: WeatherViewModel by viewModels { getViewModelFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        )

        setContent {

            val navController = rememberNavController()
            val viewModel = MainViewModel()
            NavigationAppHost(viewModel, weatherViewModel)
//            DsRegistration(navController, viewModel)
        }
    }
}