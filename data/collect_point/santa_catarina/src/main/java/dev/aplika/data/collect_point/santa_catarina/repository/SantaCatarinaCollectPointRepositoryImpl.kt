package dev.aplika.data.collect_point.santa_catarina.repository

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.data.collect_point.santa_catarina.datasource.SantaCatarinaCollectPointLocalDataSource
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import dev.aplika.core.domain.repository.SantaCatarinaCollectPointRepository
import dev.aplika.data.collect_point.santa_catarina.datasource.SantaCatarinaCollectPointRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SantaCatarinaCollectPointRepositoryImpl @Inject constructor(
    private val remoteDataSource: SantaCatarinaCollectPointRemoteDataSource,
    private val localDataSource: SantaCatarinaCollectPointLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : SantaCatarinaCollectPointRepository {

    override fun getAll(): Flow<List<SantaCatarinaCollectPoint>> {
        return localDataSource.getAll()
    }

    override fun getById(id: String): Flow<SantaCatarinaCollectPoint> {
        return localDataSource.getById(id = id)
    }

    override fun fetch(): Flow<Unit> {
        return flow { emit(remoteDataSource.getAll()) }
            .onEach { localDataSource.insertAll(collectPoints = it) }
            .map {  }
            .flowOn(defaultDispatcher)
    }
}