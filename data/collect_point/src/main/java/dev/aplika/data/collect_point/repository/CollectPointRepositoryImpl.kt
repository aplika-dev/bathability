package dev.aplika.data.collect_point.repository

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.data.collect_point.datasource.CollectPointLocalDataSource
import dev.aplika.data.collect_point.datasource.CollectPointRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectPointRemoteDataSource,
    private val localDataSource: CollectPointLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CollectPointRepository {

    override fun getAll(): Flow<List<CollectPoint>> {
        return localDataSource.getAll()
    }

    override fun fetch(): Flow<Unit> {
        return flow { emit(remoteDataSource.getAll()) }
            .onEach { localDataSource.insertAll(collectPoints = it) }
            .map {  }
            .flowOn(defaultDispatcher)
    }
}