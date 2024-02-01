package dev.aplika.core.domain.model

data class CollectPoint(
    val id: CollectPointId,
    val latitude: Double,
    val longitude: Double,
    val latestBathabilityStatus: BathabilityStatus
)
