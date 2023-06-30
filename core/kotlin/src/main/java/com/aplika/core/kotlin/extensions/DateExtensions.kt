package com.aplika.core.kotlin.extensions

import java.text.SimpleDateFormat
import java.util.Date

fun Date.formatToString(format: String) : String {
    return SimpleDateFormat(format, LOCALE_BRAZIL).format(this)
}