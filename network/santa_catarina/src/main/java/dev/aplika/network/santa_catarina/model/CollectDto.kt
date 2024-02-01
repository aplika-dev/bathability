package dev.aplika.network.santa_catarina.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectDto(
    @SerialName("DATA")
    val date: String,
    @SerialName("CONDICAO")
    val bathabilityStatus: String,
    @SerialName("CHUVA")
    val rainStatus: String,
    @SerialName("RESULTADO")
    val escherichiaColiFactor: String
)
