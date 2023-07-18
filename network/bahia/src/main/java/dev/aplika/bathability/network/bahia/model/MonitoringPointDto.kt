package dev.aplika.bathability.network.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonitoringPointDto(
    @SerialName("id")
    val id: Int,
    @SerialName("histResultado")
    val collects: List<MonitoringPointCollectDto>
)
