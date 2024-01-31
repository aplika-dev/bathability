package dev.aplika.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class SantaCatarinaCollectPointWithCollectsEntity(
    @Embedded val collectPoint: SantaCatarinaCollectPointEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "collect_point_id"
    )
    val collects: List<SantaCatarinaCollectEntity>
)
