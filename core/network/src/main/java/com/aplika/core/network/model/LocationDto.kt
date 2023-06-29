package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("CODIGO")
    val id: String,
    @SerialName("NOME")
    val name: String,
    @SerialName("OCULTAR")
    val shouldHide: String,
    @SerialName("LOCAL_ID")
    val beachId: String,
    @SerialName("LOCALIZACAO")
    val description: String,
    @SerialName("LATITUDE")
    val latitude: String,
    @SerialName("LONGITUDE")
    val longitude: String,
    @SerialName("CONDICAO")
    val situation: String
)
