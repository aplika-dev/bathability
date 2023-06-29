package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CityEntity
import com.aplika.core.database.model.LocationEntity
import com.aplika.core.domain.model.City
import com.aplika.core.domain.model.Location
import javax.inject.Inject

class LocationEntityToLocationMapper @Inject constructor() : Mapper<LocationEntity, Location> {
    override fun map(input: LocationEntity): Location {
        return Location(
            id = input.id,
            name = input.name,
            shouldHide = input.shouldHide,
            beachId = input.beachId,
            description = input.description,
            latitude = input.latitude,
            longitude = input.longitude,
            isBathable = input.isBathable
        )
    }
}