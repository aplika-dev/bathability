package com.aplika.core.kotlin.extensions

val Int.Companion.ZERO: Int get() = 0

fun Int?.orZero(): Int = this ?: Int.ZERO