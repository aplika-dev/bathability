package com.aplika.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetLocationsBodyDto(
    @SerialName("localID")
    val beachId: String
)
