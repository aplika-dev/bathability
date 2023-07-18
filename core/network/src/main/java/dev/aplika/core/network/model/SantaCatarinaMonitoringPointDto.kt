package dev.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SantaCatarinaMonitoringPointDto(
    @SerialName("CODIGO")
    val cityId: String,
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
    val collects: List<BrScMonitoringPointCollectDto>
)
