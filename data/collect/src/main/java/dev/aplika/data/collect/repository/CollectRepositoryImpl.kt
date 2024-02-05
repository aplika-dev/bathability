package dev.aplika.data.collect.repository

import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectRepository
import dev.aplika.data.collect.datasource.CollectLocalDataSource
import dev.aplika.data.collect.datasource.CollectRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.map

@Singleton
class CollectRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectRemoteDataSource,
    private val localDataSource: CollectLocalDataSource
) : CollectRepository {

    override fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<Collect>> {
        return localDataSource.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)
            .map { it ?: remoteDataSource.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup) }
    }

    override suspend fun insertAll(items: List<CollectPointWithCollects>) {
        localDataSource.insertAll(items = items)
    }
}