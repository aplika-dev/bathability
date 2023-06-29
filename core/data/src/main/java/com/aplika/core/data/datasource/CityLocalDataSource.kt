package com.aplika.core.data.datasource

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.mapper.CityEntityToCityMapper
import com.aplika.core.database.dao.CityDao
import com.aplika.core.database.database.BathabilityDatabase
import com.aplika.core.domain.model.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityLocalDataSource @Inject constructor(
    private val cityDao: CityDao,
    private val cityEntityToCityMapper: CityEntityToCityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<City>> {
        return cityDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { cityEntityToCityMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

}