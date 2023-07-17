package dev.aplika.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class MonitoringPointWithCollectsEntity(
    @Embedded val beach: MonitoringPointEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "beach_id"
    )
    val collects: List<MonitoringPointCollectEntity>
)
