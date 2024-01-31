package dev.aplika.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class CollectPointWithCollectsEntity(
    @Embedded val collectPoint: CollectPointEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "collect_point_id"
    )
    val collects: List<CollectEntity>
)
