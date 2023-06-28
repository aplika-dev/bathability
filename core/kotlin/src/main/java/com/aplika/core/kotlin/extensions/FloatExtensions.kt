package com.aplika.core.kotlin.extensions

val Float.Companion.ZERO: Float get() = 0.0f

fun Float?.orZero(): Float = this ?: Float.ZERO