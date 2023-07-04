package dev.aplika.core.kotlin.extensions

import java.text.SimpleDateFormat
import java.util.Date

val String.Companion.EMPTY: String get() = ""

fun String.toDate(format: String): Date? {
    return SimpleDateFormat(format, LOCALE_BRAZIL).parse(this)
}