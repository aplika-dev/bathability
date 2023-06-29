package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.network.model.BeachDto
import com.aplika.core.network.model.CityDto
import javax.inject.Inject

class BeachDtoToBeachMapper @Inject constructor() : Mapper<BeachDto, Beach> {
    override fun map(input: BeachDto): Beach {
        return Beach(
            id = input.id,
            name = input.name,
            city = input.city,
            shouldHide = input.shouldHide == "N",
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble()
        )
    }
}