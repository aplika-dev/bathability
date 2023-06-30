package com.aplika.core.domain.model

data class BeachCollect(
    val id: String,
    val cityIbgeId: String,
    val city: String,
    val collectPoint: String,
    val beach: String,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val collects: List<Collect>
)
