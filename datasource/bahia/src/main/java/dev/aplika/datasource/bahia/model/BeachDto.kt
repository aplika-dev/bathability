package dev.aplika.datasource.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeachDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nome")
    val name: String,
    @SerialName("monitoramento")
    val locations: List<LocationDto>
)
