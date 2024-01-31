package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.data.collect_point.mapper.CollectPointToCollectPointEntityMapper
import dev.aplika.data.collect_point.mapper.CollectPointWithCollectsEntityToCollectPointMapper
import dev.aplika.data.collect_point.mapper.CollectWithBeachIdToCollectEntityMapper
import dev.aplika.core.database.dao.CollectPointDao
import dev.aplika.core.database.dao.CollectPointWithCollectsDao
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.domain.model.CollectPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointLocalDataSource @Inject constructor(
    private val collectPointWithCollectsDao: CollectPointWithCollectsDao,
    private val collectPointDao: CollectPointDao,
    private val collectDao: CollectDao,
    private val collectPointWithCollectsEntityToCollectPointMapper: CollectPointWithCollectsEntityToCollectPointMapper,
    private val collectWithBeachIdToCollectEntityMapper: CollectWithBeachIdToCollectEntityMapper,
    private val collectPointToCollectPointEntityMapper: CollectPointToCollectPointEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<CollectPoint>> {
        return collectPointWithCollectsDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { collectPointWithCollectsEntityToCollectPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    fun getById(id: String): Flow<CollectPoint> {
        return collectPointWithCollectsDao.getById(id = id)
            .flowOn(ioDispatcher)
            .map { collectPointWithCollectsEntityToCollectPointMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(collectPoints: List<CollectPoint>) {
        val beachEntities = withContext(defaultDispatcher) {
            collectPoints.map { collectPointToCollectPointEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            collectPointDao.insertAll(list = beachEntities)
        }

        val collectEntities = withContext(defaultDispatcher) {
            collectPoints.flatMap { beachCollect -> beachCollect.collects.map { collectWithBeachIdToCollectEntityMapper.map(input = it to beachCollect.id) } }
        }

        withContext(ioDispatcher) {
            collectDao.insertAll(list = collectEntities)
        }
    }

}