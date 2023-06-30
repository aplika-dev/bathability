package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.model.Location
import com.aplika.core.network.model.BeachDto
import com.aplika.core.network.model.CityDto
import com.aplika.core.network.model.LocationDto
import javax.inject.Inject

class LocationDtoToLocationMapper @Inject constructor() : Mapper<LocationDto, Location> {
    override fun map(input: LocationDto): Location {
        return Location(
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