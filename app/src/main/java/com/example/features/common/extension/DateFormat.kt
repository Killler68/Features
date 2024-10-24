package com.example.features.common.extension

import java.text.SimpleDateFormat
import java.util.Locale

const val DATE_FORMAT_PREVIEW = "Сегодня d MMM EEE"
const val DATE_FORMAT_HOURLY = "H:mm"
const val DATE_FORMAT_DAILY = "MMM d EEE"
const val INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm"
const val INPUT_DATE_FORMAT_DAILY = "yyyy-MM-dd"
const val LANGUAGE = "ru"

val inputDateFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
val inputDateFormatDaily = SimpleDateFormat(INPUT_DATE_FORMAT_DAILY, Locale.getDefault())
val dateFormatPreview = SimpleDateFormat(DATE_FORMAT_PREVIEW, Locale(LANGUAGE))
val dateFormatHourly = SimpleDateFormat(DATE_FORMAT_HOURLY, Locale(LANGUAGE))
val dateFormatDaily = SimpleDateFormat(DATE_FORMAT_DAILY, Locale(LANGUAGE))

fun String.dateFormatPreview(): String {
    val parseDate = inputDateFormat.parse(this)
    return parseDate.let { dateFormatPreview.format(it!!) }
}

fun String.dateFormatDaily(): String {
    val parseDate = inputDateFormatDaily.parse(this)
    return parseDate.let { dateFormatDaily.format(it!!) }
}

fun String.dateFormatHourly(): String {
    val parseDate = inputDateFormat.parse(this)
    return parseDate.let { dateFormatHourly.format(it!!) }
}

fun String.dateFormat(
    inputDate: SimpleDateFormat,
    dateFormat: SimpleDateFormat
): String {
    val parse = inputDate.parse(this)
    return parse.let { dateFormat.format(it!!) }
}

fun String.datePreview(): String = dateFormat(inputDateFormat, dateFormatPreview)
fun String.dateDaily(): String = dateFormat(inputDateFormatDaily, dateFormatDaily)
fun String.dateHourly(): String = dateFormat(inputDateFormat, dateFormatHourly)