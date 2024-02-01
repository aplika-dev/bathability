package dev.aplika.network.rio_grande_do_sul.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlagDto(
    @SerialName("ativada")
    val active: Int?,
    @SerialName("condicao")
    val condition: String?,
    @SerialName("nro")
    val number: Int?,
    @SerialName("data")
    val date: String?,
    @SerialName("hora")
    val time: String?,
    @SerialName("inicioManha")
    val morningStart: String?,
    @SerialName("finalManha")
    val morningEnd: String?,
    @SerialName("inicioTarde")
    val eveningStart: String?,
    @SerialName("finalTarde")
    val eveningEnd: String?
)
