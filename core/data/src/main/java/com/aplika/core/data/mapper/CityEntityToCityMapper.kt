package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CityEntity
import com.aplika.core.domain.model.City
import javax.inject.Inject

class CityEntityToCityMapper @Inject constructor() : Mapper<CityEntity, City> {
    override fun map(input: CityEntity): City {
        return City(
            id = input.id,
            name = input.name
        )
    }
}