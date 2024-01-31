package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "santa_catarina_collect", primaryKeys = ["collect_point_id", "date"])
data class SantaCatarinaCollectEntity(
    @ColumnInfo("collect_point_id")
    val collectPointId: String,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("bathability_status")
    val bathabilityStatus: SantaCatarinaBathabilityStatusEntity?,
    @ColumnInfo("rain_status")
    val rainStatus: SantaCatarinaRainStatusEntity,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int
)
