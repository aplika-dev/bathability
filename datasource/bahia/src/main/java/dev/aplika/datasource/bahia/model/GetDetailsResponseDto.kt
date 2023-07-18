package dev.aplika.datasource.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDetailsResponseDto(
    @SerialName("monitoramento")
    val monitoringPoints: List<MonitoringPointDto>,
    @SerialName("praias")
    val cities: List<CityDto>
)
