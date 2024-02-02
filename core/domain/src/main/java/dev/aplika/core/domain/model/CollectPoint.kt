package dev.aplika.core.domain.model

data class CollectPoint(
    val id: String,
    val localityGroup: LocalityGroup,
    val latitude: Double,
    val longitude: Double,
    val latestBathabilityStatus: BathabilityStatus
)
