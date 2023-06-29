package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.CityEntity
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import javax.inject.Inject

class BeachToBeachEntityMapper @Inject constructor() : Mapper<Beach, BeachEntity> {
    override fun map(input: Beach): BeachEntity {
        return BeachEntity(
            id = input.id,
            name = input.name,
            cityId = input.cityId,
            shouldHide = input.shouldHide,
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}