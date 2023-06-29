package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.City
import com.aplika.core.network.model.CityDto
import javax.inject.Inject

class CityDtoToCityMapper @Inject constructor() : Mapper<CityDto, City> {
    override fun map(input: CityDto): City {
        return City(
            id = input.id,
            name = input.name
        )
    }
}