package com.aplika.core.data.repository

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.android.di.IoDispatcher
import com.aplika.core.data.datasource.BeachLocalDataSource
import com.aplika.core.data.datasource.BeachRemoteDataSource
import com.aplika.core.data.datasource.CityLocalDataSource
import com.aplika.core.data.datasource.CityRemoteDataSource
import com.aplika.core.data.datasource.LocationLocalDataSource
import com.aplika.core.data.datasource.LocationRemoteDataSource
import com.aplika.core.domain.model.Beach
import com.aplika.core.domain.model.City
import com.aplika.core.domain.model.Location
import com.aplika.core.domain.repository.BeachRepository
import com.aplika.core.domain.repository.CityRepository
import com.aplika.core.domain.repository.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource,
    private val localDataSource: LocationLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : LocationRepository {

    override fun getByBeachId(beachId: String): Flow<List<Location>> {
        return localDataSource.getByBeachId(beachId = beachId)
    }

}