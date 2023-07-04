package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BeachEntity
import dev.aplika.core.database.model.BeachWithCollectsEntity
import dev.aplika.core.domain.model.BeachCollect
import javax.inject.Inject

class BeachCollectToBeachEntityMapper @Inject constructor() : Mapper<BeachCollect, BeachEntity> {
    override fun map(input: BeachCollect): BeachEntity {
        return BeachEntity(
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