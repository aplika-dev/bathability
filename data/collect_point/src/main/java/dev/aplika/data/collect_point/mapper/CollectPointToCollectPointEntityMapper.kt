package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointToCollectPointEntityMapper @Inject constructor() : Mapper<CollectPoint, CollectPointEntity> {
    override fun map(input: CollectPoint): CollectPointEntity {
        return CollectPointEntity(
            id = input.id,
            city = input.city,
            name = input.name,
            description = input.description,
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}