package dev.aplika.collect_point_detailed.datasource

import dev.aplika.collect_point_detailed.mapper.CollectPointDetailedWithCollectsEntityToCollectPointDetailedMapper
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.data.mapper.CollectPointIdToCollectPointIdEntityMapper
import dev.aplika.core.database.dao.CollectPointDetailedDao
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.CollectPointId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@Singleton
class CollectPointDetailedLocalDataSource @Inject constructor(
    private val collectPointDetailedDao: CollectPointDetailedDao,
    private val collectPointIdToCollectPointIdEntityMapper: CollectPointIdToCollectPointIdEntityMapper,
    private val collectPointDetailedWithCollectsEntityToCollectPointDetailedMapper: CollectPointDetailedWithCollectsEntityToCollectPointDetailedMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getById(id: CollectPointId): Flow<CollectPointDetailed?> {
        return flowOf(collectPointIdToCollectPointIdEntityMapper.map(input = id))
            .flowOn(defaultDispatcher)
            .flatMapLatest { collectPointDetailedDao.getById(id = it) }
            .flowOn(ioDispatcher)
            .map { entity -> entity?.let { collectPointDetailedWithCollectsEntityToCollectPointDetailedMapper.map(input = entity) } }
            .flowOn(defaultDispatcher)
    }

}