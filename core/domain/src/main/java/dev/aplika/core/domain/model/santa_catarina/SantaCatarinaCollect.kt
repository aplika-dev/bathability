package dev.aplika.core.domain.model.santa_catarina

import java.util.Date

data class SantaCatarinaCollect(
    val date: Date,
    val bathabilityStatus: SantaCatarinaBathabilityStatus,
    val rainStatus: SantaCatarinaRainStatus,
    val escherichiaColiFactor: Int
)
