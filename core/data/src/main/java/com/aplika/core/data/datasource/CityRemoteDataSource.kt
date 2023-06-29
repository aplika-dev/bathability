package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.CityDtoToCityMapper
import com.aplika.core.domain.model.City
import com.aplika.core.network.service.BathabilityService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val cityDtoToCityMapper: CityDtoToCityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<City> {
        val cityListDto = withContext(ioDispatcher) {
            bathabilityService.getCities()
        }

        return withContext(defaultDispatcher) {
            cityListDto.map { cityDtoToCityMapper.map(input = it) }
        }
    }

}