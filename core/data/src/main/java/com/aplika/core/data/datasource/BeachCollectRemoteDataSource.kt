package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.BeachCollectDtoToBeachCollectMapper
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.network.service.BathabilityService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachCollectRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val beachCollectDtoToBeachCollectMapper: BeachCollectDtoToBeachCollectMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<BeachCollect> {
        val cityListDto = withContext(ioDispatcher) {
            bathabilityService.getBeachCollects()
        }

        return withContext(defaultDispatcher) {
            cityListDto.map { beachCollectDtoToBeachCollectMapper.map(input = it) }
        }
    }

}