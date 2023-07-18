package dev.aplika.bathability.network.santacatarina.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonitoringPointCollectDto(
    @SerialName("DATA")
    val date: String,
    @SerialName("CONDICAO")
    val bathabilitySituation: String,
    @SerialName("CHUVA")
    val rainSituation: String,
    @SerialName("RESULTADO")
    val escherichiaColiFactor: String
)
