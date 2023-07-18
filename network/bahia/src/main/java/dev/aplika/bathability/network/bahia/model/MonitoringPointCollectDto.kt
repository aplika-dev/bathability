package dev.aplika.bathability.network.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonitoringPointCollectDto(
    @SerialName("data")
    val date: String,
    @SerialName("categoria")
    val category: Int
)

