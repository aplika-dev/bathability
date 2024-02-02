package dev.aplika.collect_point_detailed.datasource

import dev.aplika.collect_point_detailed.mapper.CollectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper
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

@Singleton
class CollectPointDetailedLocalDataSource @Inject constructor(
    private val collectDao: CollectDao,
    private val collectPointDetailedDao: CollectPointDetailedDao,
    private val collectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper: CollectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

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