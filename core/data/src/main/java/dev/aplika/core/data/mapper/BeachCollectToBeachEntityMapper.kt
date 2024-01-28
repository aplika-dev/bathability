package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.BeachCollect
import javax.inject.Inject

class BeachCollectToBeachEntityMapper @Inject constructor() : Mapper<BeachCollect, CollectPointEntity> {
    override fun map(input: BeachCollect): CollectPointEntity {
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