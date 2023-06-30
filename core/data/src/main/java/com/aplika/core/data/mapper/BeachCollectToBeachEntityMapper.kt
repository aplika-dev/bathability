package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.BeachWithCollectsEntity
import com.aplika.core.domain.model.BeachCollect
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