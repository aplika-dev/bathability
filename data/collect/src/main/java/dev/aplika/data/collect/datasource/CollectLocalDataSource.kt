package dev.aplika.data.collect.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.data.collect.mapper.CollectEntityToCollectMapper
import dev.aplika.data.collect.mapper.CollectWithIdAndLocalityGroupToCollectEntityMapper
import dev.aplika.data.collect.model.CollectWithIdAndLocalityGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty

@Singleton
class CollectLocalDataSource @Inject constructor(
    private val collectDao: CollectDao,
    private val collectWithIdAndLocalityGroupToCollectEntityMapper: CollectWithIdAndLocalityGroupToCollectEntityMapper,
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun insertAll(
        items: List<Collect>,
        collectPointId: String,
        localityGroup: LocalityGroup
    ): Flow<Unit> {
        return flowOf(items)
            .map { list ->
                list.map {
                    collectWithIdAndLocalityGroupToCollectEntityMapper.map(
                        input = CollectWithIdAndLocalityGroup(
                            collect = it,
                            collectPointId = collectPointId,
                            localityGroup = localityGroup
                        )
                    )
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