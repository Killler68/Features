package com.example.features.common.fragment

import androidx.fragment.app.Fragment
import com.example.features.common.contex.appComponent

fun Fragment.getViewModelFactory() =
    requireContext().applicationContext.appComponent.getViewModelFactory()