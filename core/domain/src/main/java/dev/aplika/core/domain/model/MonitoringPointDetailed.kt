package dev.aplika.core.domain.model

data class MonitoringPointDetailed(
    val id: String,
    val name: String,
    val description: String,
    val collects: List<MonitoringPointCollect>
)
