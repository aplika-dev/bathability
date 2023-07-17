package dev.aplika.core.domain.model

data class MonitoringPoint(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val bathabilitySituation: BathabilitySituation
)
