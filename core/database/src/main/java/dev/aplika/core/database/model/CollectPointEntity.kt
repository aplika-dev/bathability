package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.aplika.core.domain.model.CollectPointId

@Entity(tableName = "collect_point")
data class CollectPointEntity(
    @PrimaryKey
    val id: CollectPointIdEntity,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double,
    @ColumnInfo("latest_bathability_status")
    val latestBathabilityStatus: BathabilityStatusEntity
)
