package com.aplika.core.data.repository

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.datasource.CityLocalDataSource
import com.aplika.core.data.datasource.CityRemoteDataSource
import com.aplika.core.domain.model.City
import com.aplika.core.domain.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    private val remoteDataSource: CityRemoteDataSource,
    private val localDataSource: CityLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CityRepository {

    override fun getAll(): Flow<List<City>> {
        return localDataSource.getAll()
    }

}