package com.aplika.core.domain.model

data class CollectPointDetailed(
    val id: String,
    val name: String,
    val description: String,
    val beach: Beach,
    val city: City,
    val shouldHide: Boolean,
    val latitude: Double,
    val longitude: Double,
    val isBathable: Boolean?
)
