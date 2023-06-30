package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.CollectPointEntityToCollectPointMapper
import com.aplika.core.data.mapper.CollectPointToCollectPointEntityMapper
import com.aplika.core.database.dao.CollectPointDao
import com.aplika.core.domain.model.CollectPoint
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

    fun getByBeachId(beachId: String): Flow<List<CollectPoint>> {
        return collectPointDao.getByBeachId(beachId = beachId)
            .flowOn(ioDispatcher)
            .map { list -> list.map { collectPointEntityToCollectPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(collectPointList: List<CollectPoint>) {
        val locationEntityList = withContext(defaultDispatcher) {
            collectPointList.map { collectPointToCollectPointEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            collectPointDao.insertAll(list = locationEntityList)
        }
    }

}