package dev.aplika.datasource.bahia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectDto(
    @SerialName("data")
    val date: String,
    @SerialName("categoria")
    val category: Int
)
