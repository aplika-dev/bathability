package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.aplika.core.domain.model.LocalityGroup

@Entity(tableName = "collect_point_detailed", primaryKeys = ["id", "locality_group"])
data class CollectPointDetailedEntity(
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("locality_group")
    val localityGroup: LocalityGroup,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double
)
