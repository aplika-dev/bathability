package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CollectPointEntity
import com.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointToCollectPointEntityMapper @Inject constructor() : Mapper<CollectPoint, CollectPointEntity> {
    override fun map(input: CollectPoint): CollectPointEntity {
        return CollectPointEntity(
            id = input.id,
            name = input.name,
            shouldHide = input.shouldHide,
            beachId = input.beachId,
            description = input.description,
            latitude = input.latitude,
            longitude = input.longitude,
            isBathable = input.isBathable
        )
    }
}