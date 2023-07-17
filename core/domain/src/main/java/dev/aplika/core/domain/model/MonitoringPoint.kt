package dev.aplika.core.domain.model

data class MonitoringPoint(
    val id: String,
    val cityIbgeId: String,
    val city: String,
    val collectPoint: String,
    val beach: String,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val monitoringPointCollects: List<MonitoringPointCollect>
)
