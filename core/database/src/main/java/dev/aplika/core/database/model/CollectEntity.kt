package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "collect", primaryKeys = ["collect_point_id", "date"])
data class CollectEntity(
    @ColumnInfo("collect_point_id")
    val collectPointId: String,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("bathability_status")
    val bathabilityStatus: BathabilityStatusEntity?,
    @ColumnInfo("rain_status")
    val rainStatus: RainStatusEntity?,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int?
)
