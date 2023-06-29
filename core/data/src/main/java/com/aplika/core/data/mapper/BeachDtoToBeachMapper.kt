package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.network.model.BeachDto
import com.aplika.core.network.model.CityDto
import javax.inject.Inject

class BeachDtoToBeachMapper @Inject constructor() : Mapper<Pair<BeachDto, String>, Beach> {
    override fun map(input: Pair<BeachDto, String>): Beach {
        return Beach(
            id = input.first.id,
            name = input.first.name,
            cityId = input.second,
            shouldHide = input.first.shouldHide != "N",
            latitude = input.first.latitude.toDouble(),
            longitude = input.first.longitude.toDouble()
        )
    }
}