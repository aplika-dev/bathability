package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeachDto(
    @SerialName("CODIGO")
    val id: String,
    @SerialName("BALNEARIO")
    val name: String,
    @SerialName("MUNICIPIO")
    val city: String,
    @SerialName("OCULTO")
    val shouldHide: String,
    @SerialName("LATITUDE")
    val latitude: String,
    @SerialName("LONGITUDE")
    val longitude: String
)
