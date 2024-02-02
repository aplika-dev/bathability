package dev.aplika.collect_point_detailed.model

import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.database.model.CollectPointDetailedEntity

data class CollectPointDetailedEntityWithCollectsEntity(
    val collectPointDetailed: CollectPointDetailedEntity,
    val collects: List<CollectEntity>
)
