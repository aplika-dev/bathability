package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointMapper
import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val santaCatarinaCollectPointDtoToCollectPointMapper: SantaCatarinaCollectPointDtoToCollectPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<CollectPoint> {
        val collectPoints = withContext(ioDispatcher) {
            santaCatarinaService.getCollectPoints()
        }

        return withContext(defaultDispatcher) {
            collectPoints.map { santaCatarinaCollectPointDtoToCollectPointMapper.map(input = it) }
        }
    }

}