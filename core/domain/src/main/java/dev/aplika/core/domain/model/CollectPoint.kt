package dev.aplika.core.domain.model

data class CollectPoint(
    val id: String,
    val city: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val collects: List<Collect>
)
