package com.aplika.core.data.datasource

import com.aplika.core.data.mapper.LocationDtoToLocationMapper
import com.aplika.core.domain.model.Location
import com.aplika.core.network.model.GetLocationsBodyDto
import com.aplika.core.network.service.BathabilityService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val locationDtoToLocationMapper: LocationDtoToLocationMapper
) {

    suspend fun getByBeachId(beachId: String): List<Location> {
        return bathabilityService.getLocations(
            body = GetLocationsBodyDto(
                beachId = beachId
            )
        ).map { locationDtoToLocationMapper.map(input = it) }
    }

}