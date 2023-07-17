package dev.aplika.core.domain.model

import java.util.Date

data class MonitoringPointCollect(
    val date: Date,
    val bathabilitySituation: BathabilitySituation,
    val rainSituation: RainSituation,
    val escherichiaColiFactor: Int
)
