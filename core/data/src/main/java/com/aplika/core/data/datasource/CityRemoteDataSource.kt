package com.aplika.core.data.datasource

import com.aplika.core.data.mapper.CityDtoToCityMapper
import com.aplika.core.domain.model.City
import com.aplika.core.network.service.BathabilityService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val cityDtoToCityMapper: CityDtoToCityMapper
) {

    suspend fun getAll(): List<City> {
        return bathabilityService.getCities()
            .map { cityDtoToCityMapper.map(input = it) }
    }

}