package dev.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectDto(
    @SerialName("DATA")
    val date: String,
    @SerialName("CONDICAO")
    val bathabilitySituation: String,
    @SerialName("CHUVA")
    val rainSituation: String,
    @SerialName("RESULTADO")
    val escherichiaColiFactor: String
)
