package dev.aplika.datasource.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonitoringPointDto(
    @SerialName("id")
    val id: Int,
    @SerialName("histResultado")
    val collects: List<CollectDto>
)
