package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.BeachEntityToBeachMapper
import com.aplika.core.data.mapper.BeachToBeachEntityMapper
import com.aplika.core.database.dao.BeachDao
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachLocalDataSource @Inject constructor(
    private val beachDao: BeachDao,
    private val beachEntityToBeachMapper: BeachEntityToBeachMapper,
    private val beachToBeachEntityMapper: BeachToBeachEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getByCityId(cityId: String): Flow<List<Beach>> {
        return beachDao.getByCityId(cityId = cityId)
            .flowOn(ioDispatcher)
            .map { list -> list.map { beachEntityToBeachMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(beachList: List<Beach>) {
        val beachEntityList = withContext(defaultDispatcher) {
            beachList.map { beachToBeachEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            beachDao.insertAll(list = beachEntityList)
        }
    }

}