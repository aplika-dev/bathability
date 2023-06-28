package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeachDto(
    @SerialName("codigo")
    val id: String,
    @SerialName("balneario")
    val name: String,
    @SerialName("municipio")
    val city: String,
    @SerialName("oculto")
    val shouldHide: String,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String
)
