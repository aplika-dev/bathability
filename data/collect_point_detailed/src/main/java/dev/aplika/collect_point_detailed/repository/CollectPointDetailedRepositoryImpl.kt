package dev.aplika.collect_point_detailed.repository

import dev.aplika.collect_point_detailed.datasource.CollectPointDetailedLocalDataSource
import dev.aplika.collect_point_detailed.datasource.CollectPointDetailedRemoteDataSource
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.CollectPointId
import dev.aplika.core.domain.repository.CollectPointDetailedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull

@Singleton
class CollectPointDetailedRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectPointDetailedRemoteDataSource,
    private val localDataSource: CollectPointDetailedLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CollectPointDetailedRepository {

    override fun getById(id: CollectPointId): Flow<CollectPointDetailed> {
        return localDataSource.getById(id = id)
            .filterNotNull() //TODO
            .flowOn(defaultDispatcher)
    }
}