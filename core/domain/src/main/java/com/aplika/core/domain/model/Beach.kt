package com.aplika.core.domain.model

data class Beach(
    val id: String,
    val name: String,
    val city: String,
    val shouldHide: Boolean,
    val latitude: Double,
    val longitude: Double
)
