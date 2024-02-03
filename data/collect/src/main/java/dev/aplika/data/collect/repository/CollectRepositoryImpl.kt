package dev.aplika.data.collect.repository

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.domain.repository.CollectRepository
import dev.aplika.data.collect.datasource.CollectLocalDataSource
import dev.aplika.data.collect.datasource.CollectRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf

@Singleton
class CollectRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectRemoteDataSource,
    private val localDataSource: CollectLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CollectRepository {

    override fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<Collect>> {
        return localDataSource.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup)
            .flatMapConcat { localData -> localData?.let { flowOf(it) } ?: remoteDataSource.getByIdAndLocalityGroup(id = id, localityGroup = localityGroup) }
            .flowOn(defaultDispatcher)
    }

    override fun insertAll(items: List<CollectPointWithCollects>): Flow<Unit> {
        return localDataSource.insertAll(items = items)
    }
}