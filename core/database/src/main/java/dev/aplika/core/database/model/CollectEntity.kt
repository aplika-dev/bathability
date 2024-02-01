package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "collect", primaryKeys = ["collect_point_id", "date"])
data class CollectEntity(
    @Embedded
    val collectPointId: CollectPointIdEntity,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("bathability_status")
    val bathabilityStatus: BathabilityStatusEntity,
    @ColumnInfo("rain_status")
    val rainStatus: RainStatusEntity?,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int?
)
