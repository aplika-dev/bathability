package com.aplika.core.data.repository

import com.aplika.core.data.datasource.LocationLocalDataSource
import com.aplika.core.data.datasource.LocationRemoteDataSource
import com.aplika.core.domain.model.Location
import com.aplika.core.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource,
    private val localDataSource: LocationLocalDataSource
) : LocationRepository {

    override fun getByBeachId(beachId: String): Flow<List<Location>> {
        return localDataSource.getByBeachId(beachId = beachId)
    }

    override fun getRemoteByBeachId(beachId: String): Flow<List<Location>> {
        return flow { emit(remoteDataSource.getByBeachId(beachId = beachId)) }
    }

    override fun insertAll(locationList: List<Location>): Flow<Unit> {
        return flow { emit(localDataSource.insertAll(locationList = locationList)) }
    }

}