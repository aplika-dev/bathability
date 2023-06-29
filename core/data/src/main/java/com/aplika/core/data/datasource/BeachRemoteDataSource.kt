package com.aplika.core.data.datasource

import com.aplika.core.data.mapper.BeachDtoToBeachMapper
import com.aplika.core.data.mapper.CityDtoToCityMapper
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.network.model.GetBeachesBodyDto
import com.aplika.core.network.service.BathabilityService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val beachDtoToBeachMapper: BeachDtoToBeachMapper
) {

    suspend fun getByCityId(cityId: String): List<Beach> {
        return bathabilityService.getBeaches(
            body = GetBeachesBodyDto(
                cityId = cityId
            )
        ).map { beachDtoToBeachMapper.map(input = it) }
    }

}