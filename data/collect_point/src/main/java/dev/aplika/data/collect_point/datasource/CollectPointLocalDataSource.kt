package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.database.dao.CollectPointDao
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.data.collect_point.mapper.CollectPointEntityToCollectPointMapper
import dev.aplika.data.collect_point.mapper.CollectPointToCollectPointEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

@Singleton
class CollectPointLocalDataSource @Inject constructor(
    private val collectPointDao: CollectPointDao,
    private val collectPointEntityToCollectPointMapper: CollectPointEntityToCollectPointMapper,
    private val collectPointToCollectPointEntityMapper: CollectPointToCollectPointEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPoint> {
        return collectPointDao.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)
            .flowOn(ioDispatcher)
            .map { it ?: throw IllegalStateException("Should never have null collect point and this time") }
            .map { collectPointEntityToCollectPointMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    fun getAll(): Flow<List<CollectPoint>> {
        return collectPointDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { collectPointEntityToCollectPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(items: List<CollectPoint>) {
        val collectPointEntities = mapCollectPointListToCollectPointEntities(items = items)
        insertAllOnDatabase(items = collectPointEntities)
    }

    private suspend fun mapCollectPointListToCollectPointEntities(items: List<CollectPoint>): List<CollectPointEntity> {
        return withContext(defaultDispatcher) {
            items.map { collectPointToCollectPointEntityMapper.map(input = it) }
        }
    }

    private suspend fun insertAllOnDatabase(items: List<CollectPointEntity>) {
        withContext(ioDispatcher) {
            collectPointDao.insertAll(list = items)
        }
    }

}