package com.example.assignmentproject.common.extension

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.getDate(publishedAt: String) : String?{
    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date: Date? = dateFormat.parse(publishedAt)

    val formatter: DateFormat =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return date?.let { formatter.format(it) }
}