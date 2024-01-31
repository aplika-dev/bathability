package dev.aplika.data.collect_point.santa_catarina.model

import dev.aplika.data.collect_point.santa_catarina.model.SantaCatarinaCollectDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SantaCatarinaCollectPointDto(
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
    val collects: List<SantaCatarinaCollectDto>?
)
