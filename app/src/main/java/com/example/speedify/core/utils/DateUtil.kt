package com.example.speedify.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String?.toFormattedDate(): String {
    if (this == null) return "Invalid date"

    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)

    val parsedDate = parser.parse(this)
    return if (parsedDate != null) formatter.format(parsedDate) else "Invalid date"
}