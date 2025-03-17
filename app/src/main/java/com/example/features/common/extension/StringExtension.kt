package com.example.features.common.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.example.features.ui.theme.Cyan
import java.util.Locale

fun String.firstUppercaseString(): String =
    this.substring(0, 1)
        .uppercase(Locale.getDefault()) + this.substring(1)
        .lowercase(Locale.getDefault())

fun annotatedString(
    changedText: String,
    notChangedText: String,
    fontWeight: FontWeight,
    fonStyle: FontStyle,
    fontSize: TextUnit
): AnnotatedString =
    buildAnnotatedString {
        append(changedText)
        withStyle(
            style = SpanStyle(
                fontWeight = fontWeight,
                fontStyle = fonStyle,
                fontSize = fontSize
            )
        )
        { append(notChangedText) }
    }

fun annotatedString(
    changedText: String,
    changeTextColor: Color,
    notChangedText: String,

    ): AnnotatedString =
    buildAnnotatedString {
        append(changedText)
        withStyle(
            style = SpanStyle(color = changeTextColor)
        )
        { append(notChangedText) }
    }