package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.data.mapper.BeachCollectToBeachEntityMapper
import dev.aplika.core.data.mapper.BeachWithCollectsEntityToBeachCollectMapper
import dev.aplika.core.data.mapper.CollectWithBeachIdToCollectEntityMapper
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
class BeachCollectLocalDataSource @Inject constructor(
    private val collectPointWithCollectsDao: CollectPointWithCollectsDao,
    private val collectPointDao: CollectPointDao,
    private val collectDao: CollectDao,
    private val beachWithCollectsEntityToBeachCollectMapper: BeachWithCollectsEntityToBeachCollectMapper,
    private val collectWithBeachIdToCollectEntityMapper: CollectWithBeachIdToCollectEntityMapper,
    private val beachCollectToBeachEntityMapper: BeachCollectToBeachEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<CollectPoint>> {
        return collectPointWithCollectsDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { beachWithCollectsEntityToBeachCollectMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    fun getById(id: String): Flow<CollectPoint> {
        return collectPointWithCollectsDao.getById(id = id)
            .flowOn(ioDispatcher)
            .map { beachWithCollectsEntityToBeachCollectMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(collectPoints: List<CollectPoint>) {
        val beachEntities = withContext(defaultDispatcher) {
            collectPoints.map { beachCollectToBeachEntityMapper.map(input = it) }
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