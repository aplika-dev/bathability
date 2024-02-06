package dev.aplika.data.collect_point.repository

import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectPointRepository
import dev.aplika.data.collect_point.datasource.CollectPointLocalDataSource
import dev.aplika.data.collect_point.datasource.CollectPointRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectPointRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectPointRemoteDataSource,
    private val localDataSource: CollectPointLocalDataSource
) : CollectPointRepository {

    override fun getAll(): Flow<List<CollectPoint>> {
        return localDataSource.getAll()
    }

    override fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPoint?> {
        return localDataSource.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)
    }

    override suspend fun fetchAllCatching() {
        val collectPoints = remoteDataSource.fetchAllCatching()
        localDataSource.insertAll(items = collectPoints)
    }
}