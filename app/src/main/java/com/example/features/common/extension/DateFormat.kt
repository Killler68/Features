package com.example.features.common.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Locale

const val DATE_FORMAT_HOURS = "H:mm "
const val DATE_FORMAT_PREVIEW = "Сегодня, d MMM EEE"
const val DATE_FORMAT_DAYS = "d MMM EEE"
const val DATE_FORMAT_DAYS_MONTH = "d MMM"
const val LANGUAGE = "ru"
const val TIME_FORMAT = 1000

@SuppressLint("SimpleDateFormat")
val dataFormatHours = SimpleDateFormat(DATE_FORMAT_HOURS)

val dateFormatPreview = SimpleDateFormat(DATE_FORMAT_PREVIEW, Locale(LANGUAGE))
val dateFormatDays = SimpleDateFormat(DATE_FORMAT_DAYS, Locale(LANGUAGE))
val dateFormatDaysMonth = SimpleDateFormat(DATE_FORMAT_DAYS_MONTH, Locale(LANGUAGE))

fun Long.formatToDayString(): String {
    return dateFormatDays.format(this * TIME_FORMAT)
}
fun Long.formatToDayMonthString(): String {
    return dateFormatDaysMonth.format(this * TIME_FORMAT)
}

fun Long.dateFormatPreview(): String {
    return dateFormatPreview.format(this * TIME_FORMAT)
}

fun Long.dateFormatHours(): String {
    return dataFormatHours.format(this * TIME_FORMAT)
}