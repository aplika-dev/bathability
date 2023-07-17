package dev.aplika.core.domain.model

data class MonitoringPoint(
    val id: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val bathabilitySituation: BathabilitySituation
)
