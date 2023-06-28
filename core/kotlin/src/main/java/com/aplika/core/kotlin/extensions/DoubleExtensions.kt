package com.aplika.core.kotlin.extensions

val Double.Companion.ZERO: Double get() = 0.0

fun Double?.orZero(): Double = this ?: Double.ZERO