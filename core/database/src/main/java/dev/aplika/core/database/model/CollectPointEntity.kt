package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.CollectPointId
import dev.aplika.core.domain.model.LocalityGroup

@Entity(tableName = "collect_point", primaryKeys = ["id", "locality_group"])
data class CollectPointEntity(
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("locality_group")
    val localityGroup: LocalityGroup,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double,
    @ColumnInfo("latest_bathability_status")
    val latestBathabilityStatus: BathabilityStatus
)
