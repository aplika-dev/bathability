package dev.aplika.bathability.network.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonitoringPointLocationDto(
    @SerialName("id")
    val id: Int,
    @SerialName("ref")
    val description: String,
    @SerialName("latlng")
    val coordinates: List<String>
)
