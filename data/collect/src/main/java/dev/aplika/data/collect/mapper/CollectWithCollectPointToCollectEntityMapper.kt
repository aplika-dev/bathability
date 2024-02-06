package dev.aplika.data.collect.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.data.collect.model.CollectWithCollectPoint
import javax.inject.Inject

class CollectWithCollectPointToCollectEntityMapper @Inject constructor() : Mapper<CollectWithCollectPoint, CollectEntity> {
    override fun map(input: CollectWithCollectPoint): CollectEntity {
        return CollectEntity(
            collectPointId = input.collectPoint.id,
            localityGroup = input.collectPoint.localityGroup,
            date = input.collect.date.time,
            bathabilityStatus = input.collect.bathabilityStatus,
            rainStatus = input.collect.rainStatus,
            escherichiaColiFactor = input.collect.escherichiaColiFactor
        )
    }
}