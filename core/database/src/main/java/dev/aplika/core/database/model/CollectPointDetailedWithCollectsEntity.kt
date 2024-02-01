package dev.aplika.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class CollectPointDetailedWithCollectsEntity(
    @Embedded val collectPointDetailed: CollectPointDetailedEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "collect_point_id"
    )
    val collects: List<CollectEntity>
)
