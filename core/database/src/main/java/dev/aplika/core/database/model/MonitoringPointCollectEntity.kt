package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.RainSituation

@Entity(tableName = "monitoring_point_collect", primaryKeys = ["monitoring_point_id", "date"])
data class MonitoringPointCollectEntity(
    @ColumnInfo("monitoring_point_id")
    val monitoringPointId: String,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("bathability_situation")
    val bathabilitySituation: BathabilitySituation,
    @ColumnInfo("rain_situation")
    val rainSituation: RainSituation,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int
)
