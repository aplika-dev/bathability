package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("codigo")
    val id: String,
    @SerialName("descricao")
    val name: String,
    @SerialName("email")
    val emails: String,
    @SerialName("ibge")
    val ibgeId: String
)
