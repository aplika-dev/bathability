package dev.aplika.network.santa_catarina.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.network.santa_catarina.mapper.CollectPointDtoToCollectPointMapper
import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val collectPointDtoToCollectPointMapper: CollectPointDtoToCollectPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<CollectPoint> {
        val cityListDto = withContext(ioDispatcher) {
            santaCatarinaService.getBeachCollects()
        }

        return withContext(defaultDispatcher) {
            cityListDto.map { collectPointDtoToCollectPointMapper.map(input = it) }
        }
    }

}