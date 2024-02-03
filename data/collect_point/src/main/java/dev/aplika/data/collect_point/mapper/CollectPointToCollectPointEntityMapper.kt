package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointToCollectPointEntityMapper @Inject constructor() : Mapper<CollectPoint, CollectPointEntity> {
    override fun map(input: CollectPoint): CollectPointEntity {
        return CollectPointEntity(
            id = input.id,
            localityGroup = input.localityGroup,
            name = input.name,
            city = input.city,
            latitude = input.latitude,
            longitude = input.longitude,
            latestBathabilityStatus = input.latestBathabilityStatus
        )
    }
}