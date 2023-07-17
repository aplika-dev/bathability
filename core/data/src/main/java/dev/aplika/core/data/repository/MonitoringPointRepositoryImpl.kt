package dev.aplika.core.data.repository

import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.data.datasource.MonitoringPointLocalDataSource
import dev.aplika.core.data.datasource.MonitoringPointRemoteDataSource
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.domain.repository.MonitoringPointRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointRepositoryImpl @Inject constructor(
    private val remoteDataSource: MonitoringPointRemoteDataSource,
    private val localDataSource: MonitoringPointLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : MonitoringPointRepository {

    override fun getAll(): Flow<List<MonitoringPoint>> {
        return localDataSource.getAll()
    }

    override fun getById(id: String): Flow<MonitoringPoint> {
        return localDataSource.getById(id = id)
    }

    override fun sync(): Flow<Unit> {
        return flow { emit(remoteDataSource.getAll()) }
            .onEach { localDataSource.insertAll(monitoringPoints = it) }
            .map {  }
            .flowOn(defaultDispatcher)
    }
}