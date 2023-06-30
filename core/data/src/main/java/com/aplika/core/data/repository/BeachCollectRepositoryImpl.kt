package com.aplika.core.data.repository

import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.data.datasource.BeachCollectLocalDataSource
import com.aplika.core.data.datasource.BeachCollectRemoteDataSource
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.domain.repository.BeachCollectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeachCollectRepositoryImpl @Inject constructor(
    private val remoteDataSource: BeachCollectRemoteDataSource,
    private val localDataSource: BeachCollectLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BeachCollectRepository {

    override fun getAll(): Flow<List<BeachCollect>> {
        return localDataSource.getAll()
    }

    override fun sync(): Flow<Unit> {
        return flow { emit(remoteDataSource.getAll()) }
            .onEach { localDataSource.insertAll(beachCollects = it) }
            .map {  }
            .flowOn(defaultDispatcher)
    }
}