package com.aplika.core.domain.model

import java.util.Date

data class Collect(
    val date: Date,
    val bathabilitySituation: BathabilitySituation,
    val rainSituation: RainSituation,
    val escherichiaColiFactor: Int
)
