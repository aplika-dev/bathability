package com.aplika.core.data.repository

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.datasource.BeachLocalDataSource
import com.aplika.core.data.datasource.BeachRemoteDataSource
import com.aplika.core.data.datasource.CityLocalDataSource
import com.aplika.core.data.datasource.CityRemoteDataSource
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachRepositoryImpl @Inject constructor(
    private val remoteDataSource: BeachRemoteDataSource,
    private val localDataSource: BeachLocalDataSource
) : BeachRepository {

    override fun getByCityId(cityId: String): Flow<List<Beach>> {
        return localDataSource.getByCityId(cityId = cityId)
    }

    override fun getRemoteByCityId(cityId: String): Flow<List<Beach>> {
        return flow { emit(remoteDataSource.getByCityId(cityId = cityId)) }
    }

    override fun insertAll(beachList: List<Beach>): Flow<Unit> {
        return flow { emit(localDataSource.insertAll(beachList = beachList)) }
    }

}