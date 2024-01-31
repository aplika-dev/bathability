package dev.aplika.data.collect_point.santa_catarina.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import dev.aplika.data.collect_point.santa_catarina.mapper.CollectPointDtoToCollectPointMapper
import dev.aplika.data.collect_point.santa_catarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SantaCatarinaCollectPointRemoteDataSource @Inject constructor(
    private val service: SantaCatarinaService,
    private val collectPointDtoToCollectPointMapper: CollectPointDtoToCollectPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<SantaCatarinaCollectPoint> {
        val collectPoints = withContext(ioDispatcher) {
            service.getCollectPoints()
        }

        return withContext(defaultDispatcher) {
            collectPoints.map { collectPointDtoToCollectPointMapper.map(input = it) }
        }
    }

}