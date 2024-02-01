package dev.aplika.network.rio_grande_do_sul.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectDto(
    @SerialName("dataColeta")
    val date: String,
    @SerialName("situacao")
    val status: Int,
    @SerialName("semana")
    val weekIndex: Int
)
