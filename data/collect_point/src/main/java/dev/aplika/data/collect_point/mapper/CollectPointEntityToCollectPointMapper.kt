package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointEntityToCollectPointMapper @Inject constructor() : Mapper<CollectPointEntity, CollectPoint> {
    override fun map(input: CollectPointEntity): CollectPoint {
        return CollectPoint(
            id = input.id,
            localityGroup = input.localityGroup,
            latitude = input.latitude,
            longitude = input.longitude,
            latestBathabilityStatus = input.latestBathabilityStatus
        )
    }
}