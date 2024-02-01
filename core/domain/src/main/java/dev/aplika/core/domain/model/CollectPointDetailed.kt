package dev.aplika.core.domain.model

data class CollectPointDetailed(
    val id: CollectPointId,
    val name: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val latestCollects: List<Collect>
)
