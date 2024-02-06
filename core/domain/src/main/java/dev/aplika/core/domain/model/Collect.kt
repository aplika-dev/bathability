package dev.aplika.core.domain.model

import java.util.Date

data class Collect(
    val date: Date,
    val bathabilityStatus: BathabilityStatus,
    val rainStatus: RainStatus?,
    val escherichiaColiFactor: Int?
)
