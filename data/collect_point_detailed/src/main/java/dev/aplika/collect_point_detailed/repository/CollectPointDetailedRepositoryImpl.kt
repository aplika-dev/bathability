package dev.aplika.collect_point_detailed.repository

import dev.aplika.collect_point_detailed.datasource.CollectPointDetailedLocalDataSource
import dev.aplika.collect_point_detailed.datasource.CollectPointDetailedRemoteDataSource
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointDetailedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.filterNotNull

@Singleton
class CollectPointDetailedRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectPointDetailedRemoteDataSource,
    private val localDataSource: CollectPointDetailedLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CollectPointDetailedRepository {

    override fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPointDetailed> {
        return localDataSource.getById(id = id, localityGroup = localityGroup)
            .filterNotNull() //TODO
            .flowOn(defaultDispatcher)
    }
}