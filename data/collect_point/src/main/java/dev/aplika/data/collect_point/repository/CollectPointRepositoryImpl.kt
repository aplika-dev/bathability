package dev.aplika.data.collect_point.repository

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.data.collect_point.datasource.CollectPointLocalDataSource
import dev.aplika.data.collect_point.datasource.CollectPointRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat

@Singleton
class CollectPointRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectPointRemoteDataSource,
    private val localDataSource: CollectPointLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CollectPointRepository {

    override fun getAll(): Flow<List<CollectPoint>> {
        return localDataSource.getAll()
    }

    override fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPoint> {
        return localDataSource.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetch(): Flow<Unit> {
        return remoteDataSource.getAll()
            .flatMapConcat { localDataSource.insertAll(collectPoints = it) }
            .flowOn(defaultDispatcher)
    }
}