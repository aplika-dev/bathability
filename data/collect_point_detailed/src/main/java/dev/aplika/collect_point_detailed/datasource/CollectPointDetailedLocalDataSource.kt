package dev.aplika.collect_point_detailed.datasource

import dev.aplika.collect_point_detailed.mapper.CollectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper
import dev.aplika.collect_point_detailed.mapper.CollectPointDetailedToCollectEntityListMapper
import dev.aplika.collect_point_detailed.mapper.CollectPointDetailedToCollectPointDetailedEntityMapper
import dev.aplika.collect_point_detailed.model.CollectPointDetailedEntityWithCollectsEntity
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.database.dao.CollectPointDetailedDao
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.database.model.CollectPointDetailedEntity
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Singleton
class CollectPointDetailedLocalDataSource @Inject constructor(
    private val collectDao: CollectDao,
    private val collectPointDetailedDao: CollectPointDetailedDao,
    private val collectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper: CollectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper,
    private val collectPointDetailedToCollectPointDetailedEntityMapper: CollectPointDetailedToCollectPointDetailedEntityMapper,
    private val collectPointDetailedToCollectEntityListMapper: CollectPointDetailedToCollectEntityListMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun insertAll(items: List<CollectPointDetailed>): Flow<Unit> {
        return combine(
            insertAllCollect(items = items),
            insertAllCollectPointDetailed(items = items),
        ) { _, _ -> }
    }

    private fun insertAllCollect(items: List<CollectPointDetailed>): Flow<Unit> {
        return flowOf(items)
            .map { list -> list.flatMap { collectPointDetailedToCollectEntityListMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
            .map { collectDao.insertAll(list = it) }
            .flowOn(ioDispatcher)
    }

    private fun insertAllCollectPointDetailed(items: List<CollectPointDetailed>): Flow<Unit> {
        return flowOf(items)
            .map { list -> list.map { collectPointDetailedToCollectPointDetailedEntityMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
            .map { collectPointDetailedDao.insertAll(list = it) }
            .flowOn(ioDispatcher)
    }

    fun getById(id: String, localityGroup: LocalityGroup): Flow<CollectPointDetailed?> {
        return getCollectPointDetailedEntityById(id = id, localityGroup = localityGroup)
            .combine(getAllByCollectPointIdAndLocalityGroup(id = id, localityGroup = localityGroup)) { collectPointDetailed, collects ->
                collectPointDetailed?.let {
                    collectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper.map(
                        input = CollectPointDetailedEntityWithCollectsEntity(
                            collectPointDetailed = it,
                            collects = collects
                        )
                    )
                }
            }
            .flowOn(defaultDispatcher)
    }

    private fun getCollectPointDetailedEntityById(id: String, localityGroup: LocalityGroup): Flow<CollectPointDetailedEntity?> {
        return collectPointDetailedDao.getCollectPointDetailedById(id = id, localityGroup = localityGroup)
            .flowOn(ioDispatcher)
    }

    private fun getAllByCollectPointIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<CollectEntity>> {
        return collectDao.getAllByCollectPointIdAndLocalityGroup(id = id, localityGroup = localityGroup)
            .flowOn(ioDispatcher)
    }

}