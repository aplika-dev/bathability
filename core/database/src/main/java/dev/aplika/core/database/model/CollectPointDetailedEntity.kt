package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collect_point_detailed")
data class CollectPointDetailedEntity(
    @PrimaryKey
    val id: CollectPointIdEntity,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double
)
