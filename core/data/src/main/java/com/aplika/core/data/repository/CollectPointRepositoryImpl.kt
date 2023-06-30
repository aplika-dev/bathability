package com.aplika.core.data.repository

import com.aplika.core.data.datasource.CollectPointLocalDataSource
import com.aplika.core.data.datasource.CollectPointRemoteDataSource
import com.aplika.core.domain.model.CollectPoint
import com.aplika.core.domain.repository.CollectPointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectPointRemoteDataSource,
    private val localDataSource: CollectPointLocalDataSource
) : CollectPointRepository {

    override fun getByBeachId(beachId: String): Flow<List<CollectPoint>> {
        return localDataSource.getByBeachId(beachId = beachId)
    }

    override fun getRemoteByBeachId(beachId: String): Flow<List<CollectPoint>> {
        return flow { emit(remoteDataSource.getByBeachId(beachId = beachId)) }
    }

    override fun insertAll(collectPointList: List<CollectPoint>): Flow<Unit> {
        return flow { emit(localDataSource.insertAll(collectPointList = collectPointList)) }
    }

}