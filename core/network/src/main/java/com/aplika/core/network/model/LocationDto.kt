package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("codigo")
    val id: String,
    @SerialName("nome")
    val name: String,
    @SerialName("ocultar")
    val shouldHide: String,
    @SerialName("local_id")
    val beachId: String,
    @SerialName("localizacao")
    val description: String,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("condicao")
    val situation: String
)
