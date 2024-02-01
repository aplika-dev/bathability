package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.database.dao.CollectPointDao
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.data.collect_point.mapper.CollectPointEntityToCollectPointMapper
import dev.aplika.data.collect_point.mapper.CollectPointToCollectPointEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointLocalDataSource @Inject constructor(
    private val collectPointDao: CollectPointDao,
    private val collectPointEntityToCollectPointMapper: CollectPointEntityToCollectPointMapper,
    private val collectPointToCollectPointEntityMapper: CollectPointToCollectPointEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<CollectPoint>> {
        return collectPointDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { collectPointEntityToCollectPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(collectPoints: List<CollectPoint>) {
        val collectPointEntities = withContext(defaultDispatcher) {
            collectPoints.map { collectPointToCollectPointEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            collectPointDao.insertAll(list = collectPointEntities)
        }
    }

}