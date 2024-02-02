package dev.aplika.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointDetailedEntity
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.CollectPointDetailed
import javax.inject.Inject

class CollectPointDetailedToCollectPointDetailedEntityMapper @Inject constructor() : Mapper<CollectPointDetailed, CollectPointDetailedEntity> {
    override fun map(input: CollectPointDetailed): CollectPointDetailedEntity {
        return CollectPointDetailedEntity(
            id = input.id,
            localityGroup = input.localityGroup,
            name = input.name,
            city = input.city,
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}