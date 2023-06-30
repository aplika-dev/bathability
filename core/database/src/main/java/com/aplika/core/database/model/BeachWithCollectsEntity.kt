package com.aplika.core.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class BeachWithCollectsEntity(
    @Embedded val beach: BeachEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "beach_id"
    )
    val collects: List<CollectEntity>
)
