package com.aplika.core.domain.model

data class Location(
    val id: String,
    val name: String,
    val shouldHide: Boolean,
    val beachId: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val isBathable: Boolean
)
