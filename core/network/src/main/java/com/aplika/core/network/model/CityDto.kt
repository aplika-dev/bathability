package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("CODIGO")
    val id: String,
    @SerialName("DESCRICAO")
    val name: String,
    @SerialName("EMAIL")
    val emails: String,
    @SerialName("IBGE")
    val ibgeId: String
)
