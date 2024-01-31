package dev.aplika.core.domain.model.santa_catarina

data class SantaCatarinaCollectPoint(
    val id: String,
    val city: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val collects: List<SantaCatarinaCollect>
)
