package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.CollectPoint
import com.aplika.core.network.model.LocationDto
import javax.inject.Inject

class CollectPointDtoToCollectPointMapper @Inject constructor() : Mapper<LocationDto, CollectPoint> {
    override fun map(input: LocationDto): CollectPoint {
        return CollectPoint(
            id = input.id,
            name = input.name,
            beachId = input.beachId,
            shouldHide = input.shouldHide != "N",
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            isBathable = when (input.situation) {
                "PRÓPRIO" -> true
                "IMPRÓPRIO" -> false
                else -> null
            },
            description = input.description
        )
    }
}