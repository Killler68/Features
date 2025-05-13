package com.example.features.common.strings

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun toast(context: Context, @StringRes text: Int) = Toast.makeText(context, text, Toast.LENGTH_LONG).show() // no sense, utils?
