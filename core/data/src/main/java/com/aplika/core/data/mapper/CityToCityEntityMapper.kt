package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CityEntity
import com.aplika.core.domain.model.City
import javax.inject.Inject

class CityToCityEntityMapper @Inject constructor() : Mapper<City, CityEntity> {
    override fun map(input: City): CityEntity {
        return CityEntity(
            id = input.id,
            name = input.name
        )
    }
}