package dev.aplika.bathability.network.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDetailsDto(
    @SerialName("monitoramento")
    val monitoringPoints: List<MonitoringPointDto>,
    @SerialName("praias")
    val cities: List<CityDto>
)

