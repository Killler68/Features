package com.example.features.common.extension

import java.util.Locale

fun String.firstUppercaseString(): String =
    this.substring(0, 1)
        .uppercase(Locale.getDefault()) + this.substring(1)
        .lowercase(Locale.getDefault())

