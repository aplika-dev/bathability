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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachRepositoryImpl @Inject constructor(
    private val remoteDataSource: BeachRemoteDataSource,
    private val localDataSource: BeachLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BeachRepository {

    override fun getByCityId(cityId: String): Flow<List<Beach>> {
        return localDataSource.getByCityId(cityId = cityId)
    }

}