package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.aplika.core.domain.model.BathabilitySituation

@Entity(tableName = "monitoring_point")
data class MonitoringPointEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double,
    @ColumnInfo("bathability_situation")
    val bathabilitySituation: BathabilitySituation
)
