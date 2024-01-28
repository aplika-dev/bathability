package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class BeachCollectToBeachEntityMapper @Inject constructor() : Mapper<CollectPoint, CollectPointEntity> {
    override fun map(input: CollectPoint): CollectPointEntity {
        return CollectPointEntity(
            id = input.id,
            cityIbgeId = input.cityIbgeId,
            city = input.city,
            collectPoint = input.collectPoint,
            beach = input.beach,
            location = input.location,
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}