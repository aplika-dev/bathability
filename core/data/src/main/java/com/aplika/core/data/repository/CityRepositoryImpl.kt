package com.aplika.core.data.repository

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.datasource.CityLocalDataSource
import com.aplika.core.data.datasource.CityRemoteDataSource
import com.aplika.core.domain.model.City
import com.aplika.core.domain.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    private val remoteDataSource: CityRemoteDataSource,
    private val localDataSource: CityLocalDataSource
) : CityRepository {

    override fun get(): Flow<List<City>> {
        return localDataSource.getAll()
    }

    override fun getRemote(): Flow<List<City>> {
        return flow { emit(remoteDataSource.getAll()) }
    }

    override fun insertAll(cityList: List<City>): Flow<Unit> {
        return flow { emit(localDataSource.insertAll(cityList = cityList)) }
    }

}