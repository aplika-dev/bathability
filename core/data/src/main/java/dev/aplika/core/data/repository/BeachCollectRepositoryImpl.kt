package dev.aplika.core.data.repository

import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.data.datasource.BeachCollectLocalDataSource
import dev.aplika.core.data.datasource.BeachCollectRemoteDataSource
import dev.aplika.core.domain.model.BeachCollect
import dev.aplika.core.domain.repository.BeachCollectRepository
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

    override fun getById(id: String): Flow<BeachCollect> {
        return localDataSource.getById(id = id)
    }

    override fun sync(): Flow<Unit> {
        return flow { emit(remoteDataSource.getAll()) }
            .onEach { localDataSource.insertAll(beachCollects = it) }
            .map {  }
            .flowOn(defaultDispatcher)
    }
}