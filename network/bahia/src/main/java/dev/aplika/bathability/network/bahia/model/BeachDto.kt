package dev.aplika.bathability.network.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeachDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nome")
    val name: String,
    @SerialName("monitoramento")
    val monitoringPoints: List<MonitoringPointLocationDto>
)
