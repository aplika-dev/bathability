package dev.aplika.network.rio_grande_do_sul.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectPointDto(
    @SerialName("codLocal")
    val id: Long,
    @SerialName("nome")
    val name: String,
    @SerialName("nomeMunicipio")
    val cityName: String,
    @SerialName("laboratorio")
    val laboratoryName: String,
    @SerialName("situacao")
    val status: Int,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("bandeira")
    val flag: FlagDto
)
