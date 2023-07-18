package dev.aplika.datasource.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("id")
    val id: Int,
    @SerialName("ref")
    val description: String,
    @SerialName("latlng")
    val latLng: List<String>
)
