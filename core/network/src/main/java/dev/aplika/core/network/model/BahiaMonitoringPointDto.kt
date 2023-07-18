package dev.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BahiaMonitoringPointDto(
    @SerialName("id")
    val id: Int,
    @SerialName("praia")
    val beachName: String,
    @SerialName("ref")
    val location: String,
    @SerialName("latlng")
    val latLng: List<String>,
    @SerialName("categoria")
    val category: Int
)
