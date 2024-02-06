package dev.aplika.core.domain.model

data class CollectPoint(
    val id: String,
    val localityGroup: LocalityGroup,
    val name: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val latestBathabilityStatus: BathabilityStatus
)
