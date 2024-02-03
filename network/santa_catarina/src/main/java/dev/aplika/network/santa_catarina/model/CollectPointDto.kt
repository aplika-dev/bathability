package dev.aplika.network.santa_catarina.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectPointDto(
    @SerialName("CODIGO")
    val id: String,
    @SerialName("MUNICIPIO_COD_IBGE")
    val cityIbgeId: String,
    @SerialName("MUNICIPIO")
    val city: String,
    @SerialName("PONTO_NOME")
    val collectPoint: String,
    @SerialName("BALNEARIO")
    val beach: String,
    @SerialName("LOCALIZACAO")
    val location: String,
    @SerialName("LATITUDE")
    val latitude: String,
    @SerialName("LONGITUDE")
    val longitude: String,
    @SerialName("ANALISES")
    val collects: List<CollectDto>?
)
