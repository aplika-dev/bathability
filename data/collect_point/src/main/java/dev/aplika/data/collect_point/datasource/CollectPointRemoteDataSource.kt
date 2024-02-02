package dev.aplika.data.collect_point.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.repository.CollectPointDetailedRepository
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointDetailedMapper
import dev.aplika.data.collect_point.mapper.SantaCatarinaCollectPointDtoToCollectPointMapper
import dev.aplika.network.santa_catarina.model.CollectPointDto
import dev.aplika.network.santa_catarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Singleton
class CollectPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val santaCatarinaCollectPointDtoToCollectPointMapper: SantaCatarinaCollectPointDtoToCollectPointMapper,
    private val santaCatarinaCollectPointDtoToCollectPointDetailedMapper: SantaCatarinaCollectPointDtoToCollectPointDetailedMapper,
    private val collectPointDetailedRepository: CollectPointDetailedRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<CollectPoint>> {
        return getAllFromSantaCatarina()
    }

    private fun getAllFromSantaCatarina(): Flow<List<CollectPoint>> {
        return flow { emit(santaCatarinaService.getCollectPoints()) }
            .flowOn(ioDispatcher)
            .onEach { insertAllFromSantaCatarinaOnCollectPointDetailed(collectPoints = it) }
            .map { collectPoints -> collectPoints.map { santaCatarinaCollectPointDtoToCollectPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun insertAllFromSantaCatarinaOnCollectPointDetailed(collectPoints: List<CollectPointDto>) {
        return flowOf(collectPoints)
            .map { list -> list.map { santaCatarinaCollectPointDtoToCollectPointDetailedMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
            .flatMapConcat { collectPointDetailedRepository.insertAll(items = it) }
            .collect()
    }

}