package com.aplika.core.android.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}