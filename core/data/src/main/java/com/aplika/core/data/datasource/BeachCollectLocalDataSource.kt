package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.BeachCollectToBeachEntityMapper
import com.aplika.core.data.mapper.BeachWithCollectsEntityToBeachCollectMapper
import com.aplika.core.data.mapper.CollectWithBeachIdToCollectEntityMapper
import com.aplika.core.database.dao.BeachDao
import com.aplika.core.database.dao.BeachWithCollectsDao
import com.aplika.core.database.dao.CollectDao
import com.aplika.core.domain.model.BeachCollect
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