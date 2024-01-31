package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.data.collect_point.santa_catarina.mapper.CollectPointToCollectPointEntityMapper
import dev.aplika.data.collect_point.santa_catarina.mapper.CollectPointWithCollectsEntityToCollectPointMapper
import dev.aplika.data.collect_point.santa_catarina.mapper.CollectWithBeachIdToCollectEntityMapper
import dev.aplika.core.database.dao.SantaCatarinaCollectPointDao
import dev.aplika.core.database.dao.SantaCatarinaCollectPointWithCollectsDao
import dev.aplika.core.database.dao.SantaCatarinaCollectDao
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SantaCatarinaCollectPointLocalDataSource @Inject constructor(
    private val santaCatarinaCollectPointWithCollectsDao: SantaCatarinaCollectPointWithCollectsDao,
    private val santaCatarinaCollectPointDao: SantaCatarinaCollectPointDao,
    private val santaCatarinaCollectDao: SantaCatarinaCollectDao,
    private val collectPointWithCollectsEntityToCollectPointMapper: CollectPointWithCollectsEntityToCollectPointMapper,
    private val collectWithBeachIdToCollectEntityMapper: CollectWithBeachIdToCollectEntityMapper,
    private val collectPointToCollectPointEntityMapper: CollectPointToCollectPointEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<SantaCatarinaCollectPoint>> {
        return santaCatarinaCollectPointWithCollectsDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { collectPointWithCollectsEntityToCollectPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    fun getById(id: String): Flow<SantaCatarinaCollectPoint> {
        return santaCatarinaCollectPointWithCollectsDao.getById(id = id)
            .flowOn(ioDispatcher)
            .map { collectPointWithCollectsEntityToCollectPointMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(collectPoints: List<SantaCatarinaCollectPoint>) {
        val beachEntities = withContext(defaultDispatcher) {
            collectPoints.map { collectPointToCollectPointEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            santaCatarinaCollectPointDao.insertAll(list = beachEntities)
        }

        val collectEntities = withContext(defaultDispatcher) {
            collectPoints.flatMap { beachCollect -> beachCollect.collects.map { collectWithBeachIdToCollectEntityMapper.map(input = it to beachCollect.id) } }
        }

        withContext(ioDispatcher) {
            santaCatarinaCollectDao.insertAll(list = collectEntities)
        }
    }

}