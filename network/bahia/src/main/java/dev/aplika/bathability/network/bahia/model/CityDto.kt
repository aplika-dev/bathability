package dev.aplika.bathability.network.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("id")
    val id: String,
    @SerialName("cidade")
    val name: String,
    @SerialName("idCosta")
    val regionId: Int,
    @SerialName("nomeCosta")
    val regionName: String,
    @SerialName("praias")
    val beaches: List<BeachDto>
)
