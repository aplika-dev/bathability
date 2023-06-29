package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CityEntity
import com.aplika.core.database.model.LocationEntity
import com.aplika.core.domain.model.City
import com.aplika.core.domain.model.Location
import javax.inject.Inject

class LocationToLocationEntityMapper @Inject constructor() : Mapper<Location, LocationEntity> {
    override fun map(input: Location): LocationEntity {
        return LocationEntity(
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