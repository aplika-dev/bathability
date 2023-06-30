package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.model.CollectPoint
import com.aplika.core.domain.model.CollectPointDetailed
import javax.inject.Inject

class CityBeachCollectPointToCollectPointDetailedMapper @Inject constructor() : Mapper<Triple<City, Beach, CollectPoint>, CollectPointDetailed> {
    override fun map(input: Triple<City, Beach, CollectPoint>): CollectPointDetailed {
        return CollectPointDetailed(
            id = input.third.id,
            name = input.third.name,
            description = input.third.description,
            beach = input.second,
            city = input.first,
            shouldHide = input.third.shouldHide,
            latitude = input.third.latitude,
            longitude = input.third.longitude,
            isBathable = input.third.isBathable
        )
    }
}