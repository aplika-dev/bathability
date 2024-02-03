package dev.aplika.data.collect.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.data.collect.mapper.CollectEntityToCollectMapper
import dev.aplika.data.collect.mapper.CollectWithCollectPointToCollectEntityMapper
import dev.aplika.data.collect.model.CollectWithCollectPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Singleton
class CollectLocalDataSource @Inject constructor(
    private val collectDao: CollectDao,
    private val collectWithCollectPointToCollectEntityMapper: CollectWithCollectPointToCollectEntityMapper,
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun insertAll(items: List<CollectPointWithCollects>): Flow<Unit> {
        return flowOf(items)
            .map { collectPointWithCollects ->
                collectPointWithCollects.flatMap { collectPointWithCollect ->
                    collectPointWithCollect.collects.map { collect ->
                        collectWithCollectPointToCollectEntityMapper.map(
                            input = CollectWithCollectPoint(
                                collect = collect,
                                collectPoint = collectPointWithCollect.collectPoint
                            )
                        )
                    }
                }
            }
            .flowOn(defaultDispatcher)
            .map { collectDao.insertAll(list = it) }
            .flowOn(ioDispatcher)
    }

    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<Collect>?> {
        return collectDao.getAllByCollectPointIdAndLocalityGroup(id = id, localityGroup = localityGroup)
            .flowOn(ioDispatcher)
            .map { list -> list.map { collectEntityToCollectMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
            .map { it.ifEmpty { null } }
    }

}