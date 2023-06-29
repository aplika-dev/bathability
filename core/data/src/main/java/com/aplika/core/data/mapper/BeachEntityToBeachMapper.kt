package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.CityEntity
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import javax.inject.Inject

class BeachEntityToBeachMapper @Inject constructor() : Mapper<BeachEntity, Beach> {
    override fun map(input: BeachEntity): Beach {
        return Beach(
            id = input.id,
            name = input.name,
            city = input.city,
            shouldHide = input.shouldHide,
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}