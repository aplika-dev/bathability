package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.data.mapper.BeachCollectToBeachEntityMapper
import dev.aplika.core.data.mapper.BeachWithCollectsEntityToBeachCollectMapper
import dev.aplika.core.data.mapper.CollectWithBeachIdToCollectEntityMapper
import dev.aplika.core.database.dao.BeachDao
import dev.aplika.core.database.dao.BeachWithCollectsDao
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.domain.model.BeachCollect
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachCollectLocalDataSource @Inject constructor(
    private val beachWithCollectsDao: BeachWithCollectsDao,
    private val beachDao: BeachDao,
    private val collectDao: CollectDao,
    private val beachWithCollectsEntityToBeachCollectMapper: BeachWithCollectsEntityToBeachCollectMapper,
    private val collectWithBeachIdToCollectEntityMapper: CollectWithBeachIdToCollectEntityMapper,
    private val beachCollectToBeachEntityMapper: BeachCollectToBeachEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<BeachCollect>> {
        return beachWithCollectsDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { beachWithCollectsEntityToBeachCollectMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    fun getById(id: String): Flow<BeachCollect> {
        return beachWithCollectsDao.getById(id = id)
            .flowOn(ioDispatcher)
            .map { beachWithCollectsEntityToBeachCollectMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(beachCollects: List<BeachCollect>) {
        val beachEntities = withContext(defaultDispatcher) {
            beachCollects.map { beachCollectToBeachEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            beachDao.insertAll(list = beachEntities)
        }

        val collectEntities = withContext(defaultDispatcher) {
            beachCollects.flatMap { beachCollect -> beachCollect.collects.map { collectWithBeachIdToCollectEntityMapper.map(input = it to beachCollect.id) } }
        }

        withContext(ioDispatcher) {
            collectDao.insertAll(list = collectEntities)
        }
    }

}