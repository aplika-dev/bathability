package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.BeachDtoToBeachMapper
import com.aplika.core.data.mapper.CityDtoToCityMapper
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.network.model.GetBeachesBodyDto
import com.aplika.core.network.service.BathabilityService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val beachDtoToBeachMapper: BeachDtoToBeachMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getByCityId(cityId: String): List<Beach> {
        val beachListDto = withContext(ioDispatcher) {
            bathabilityService.getBeaches(
                body = GetBeachesBodyDto(
                    cityId = cityId
                )
            )
        }

        return withContext(defaultDispatcher) {
            beachListDto.map { beachDtoToBeachMapper.map(input = it to cityId) }
        }
    }

}