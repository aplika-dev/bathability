package com.aplika.core.kotlin.extensions

val Long.Companion.ZERO: Long get() = 0L

fun Long?.orZero(): Long = this ?: Long.ZERO