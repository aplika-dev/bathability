package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.model.RainStatus

@Entity(tableName = "collect", primaryKeys = ["collect_point_id", "locality_group", "date"])
data class CollectEntity(
    @ColumnInfo("collect_point_id")
    val collectPointId: String,
    @ColumnInfo("locality_group")
    val localityGroup: LocalityGroup,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("bathability_status")
    val bathabilityStatus: BathabilityStatus,
    @ColumnInfo("rain_status")
    val rainStatus: RainStatus?,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int?
)
