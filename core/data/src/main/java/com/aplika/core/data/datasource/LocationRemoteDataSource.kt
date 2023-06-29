package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.LocationDtoToLocationMapper
import com.aplika.core.domain.model.Location
import com.aplika.core.network.model.GetLocationsBodyDto
import com.aplika.core.network.service.BathabilityService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val locationDtoToLocationMapper: LocationDtoToLocationMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getByBeachId(beachId: String): List<Location> {
        val beachListDto = withContext(ioDispatcher) {
            bathabilityService.getLocations(
                body = GetLocationsBodyDto(
                    beachId = beachId
                )
            )
        }

        return withContext(defaultDispatcher) {
            beachListDto.map { locationDtoToLocationMapper.map(input = it) }
        }
    }

}