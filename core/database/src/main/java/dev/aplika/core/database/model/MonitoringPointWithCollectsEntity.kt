package dev.aplika.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class MonitoringPointWithCollectsEntity(
    @Embedded val monitoringPoint: MonitoringPointEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "monitoring_point_id"
    )
    val collects: List<MonitoringPointCollectEntity>
)
