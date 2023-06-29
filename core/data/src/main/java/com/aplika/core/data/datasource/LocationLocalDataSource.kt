package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.BeachEntityToBeachMapper
import com.aplika.core.data.mapper.LocationEntityToLocationMapper
import com.aplika.core.database.dao.BeachDao
import com.aplika.core.database.dao.LocationDao
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.model.Location
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationLocalDataSource @Inject constructor(
    private val locationDao: LocationDao,
    private val locationEntityToLocationMapper: LocationEntityToLocationMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getByBeachId(beachId: String): Flow<List<Location>> {
        return locationDao.getByBeachId(beachId = beachId)
            .flowOn(ioDispatcher)
            .map { list -> list.map { locationEntityToLocationMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

}