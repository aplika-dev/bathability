package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.CollectPointDtoToCollectPointMapper
import com.aplika.core.domain.model.CollectPoint
import com.aplika.core.network.service.BathabilityService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointRemoteDataSource @Inject constructor(
    private val bathabilityService: BathabilityService,
    private val collectPointDtoToCollectPointMapper: CollectPointDtoToCollectPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getByBeachId(beachId: String): List<CollectPoint> {
        val beachListDto = withContext(ioDispatcher) {
            bathabilityService.getLocations(beachId = beachId.toInt())
        }

        return withContext(defaultDispatcher) {
            beachListDto.map { collectPointDtoToCollectPointMapper.map(input = it) }
        }
    }

}